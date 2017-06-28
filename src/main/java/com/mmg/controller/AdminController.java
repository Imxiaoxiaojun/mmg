package com.mmg.controller;

import com.mmg.common.Constants;
import com.mmg.common.Page;
import com.mmg.entity.admin.Admin;
import com.mmg.entity.admin.Role;
import com.mmg.entity.admin.Rule;
import com.mmg.entity.common.IpAddress;
import com.mmg.entity.common.Weather;
import com.mmg.entity.common.Weather24H;
import com.mmg.service.AdminService;
import com.mmg.service.mmg.IpService;
import com.mmg.service.mmg.WeatherService;
import com.mmg.util.CommonUtil;
import com.mmg.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by yj on 2017/5/13.
 */
@Controller
public class AdminController {
	@Autowired
	@Qualifier("adminService")
	private AdminService adminService;
	@Autowired
	@Qualifier("weatherService")
	private WeatherService weatherService;

	@Autowired
	@Qualifier("ipService")
	private IpService ipService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "adminConsole.hel")
	public String getMain(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String ip = (String) request.getSession().getAttribute("clientIp");
		IpAddress ipAddress = ipService.queryLocalByIp(ip);

		String area = "";
		if (null != ipAddress) {
			area = ipAddress.getRegion();
		}
		Weather weather = weatherService.get24HourInfo(area);

		if (weather == null||weather.getHourList()==null|| weather.getHourList().size()<=0) return "admin/main.vm";

		Map<String,Object> map = (Map<String, Object>) weather.getHourList().get(0);
		Weather24H weather24 = new Weather24H();
		CommonUtil.mapToBean(map,weather24);
		String weatherTime = weather24.getTime();

		if(weatherTime == null)return "admin/main.vm";
		if(DateUtil.isDay(weatherTime.substring(weatherTime.length()-4, weatherTime.length()))){
			request.getSession().setAttribute("dayCode", weather24.getWeather_code());
		}else{
			request.getSession().setAttribute("nightCode", weather24.getWeather_code());
		}
		request.getSession().setAttribute("weather", weather24);
		return "admin/main.vm";
	}

	@RequestMapping(value = "admin/getTop.hel", method = RequestMethod.GET)
	public String getTop(HttpServletRequest request, ModelMap model) {
		model.put("userName", request.getSession().getAttribute("userName"));
		return "admin/top.vm";
	}

	@RequestMapping(value = "admin/getLeft.hel", method = RequestMethod.GET)
	public String getLeft(HttpServletRequest request, ModelMap model) {
		Admin admin = adminService.getAdminInfo((String) request.getSession().getAttribute("userName"));
		List<Role> roleList = adminService.getRoleList(admin.getId());
		List<Object> roleIdlist = CommonUtil.getFieldList(roleList, Role.class, "", "id");
		List<Rule> ruleList = adminService.getRuleList(roleIdlist);
		Map<String, List<Rule>> menuMap = CommonUtil.groupMenuListByFiled(ruleList);
		model.putAll(menuMap);
		return "admin/left.vm";
	}

	@RequestMapping(value = "admin/getIndex.hel", method = RequestMethod.GET)
	public String getIndex(HttpServletRequest request, ModelMap model) {
		Admin admin = adminService.getAdminInfo((String) request.getSession().getAttribute("userName"));
		model.put("dayCode", request.getSession().getAttribute("dayCode"));
		model.put("nightCode", request.getSession().getAttribute("nightCode"));
		model.put("weather", request.getSession().getAttribute("weather"));
		model.put("adminInfo", admin);
		
		model.put("lastLoginTime",request.getSession().getAttribute("lastLoginTime"));
		model.put("lastLoginIp",request.getSession().getAttribute("lastLoginIp"));
		model.put("lastLoginLocale",ipService.queryLocalByIp((String)request.getSession().getAttribute("lastLoginIp")).getRegion());
		return "admin/index.vm";
	}

	@RequestMapping(value = "admin/admin_logout.hel", method = RequestMethod.GET)
	public String adminLogout(HttpServletRequest request, ModelMap model) {
		request.getSession().removeAttribute(Constants.USERNAME);
		return "redirect:/adminLogin.hel";
	}

	@RequestMapping(value = "admin/getRuleList.hel", method = RequestMethod.GET)
	public String getRuleList(HttpServletRequest request, Integer curpage, Integer pagesize, ModelMap model) {
		// List<Rule> ruleList = adminService.getAllRuleList(Rule.class);
		/*
		 * model.put("ruleList",ruleList); model.put("count",150);
		 * model.put("pagesize",15);
		 */
		int count = adminService.getObjectCount(Rule.class);
		Page<Rule> page = new Page();
		page.setCount(count);
		if (null != curpage && 0 < curpage)
			page.setCurPage(curpage);
		if (null != pagesize && 0 < pagesize)
			page.setPageSize(pagesize);
		List<Rule> ruleList = adminService.getPageList(Rule.class, page);
		page.setPageList(ruleList);
		model.put("pageInfo", page);
		return "/admin/ruleList.vm";
	}

	@RequestMapping(value = "admin/getRoleList.hel", method = RequestMethod.GET)
	public String getRoleList(HttpServletRequest request, ModelMap model) {
		List<Role> roleList = adminService.getAllRoleList(Role.class);
		model.put("roleList", roleList);
		return "/admin/roleList.vm";
	}

	@RequestMapping(value = "admin/getAdminList.hel", method = RequestMethod.GET)
	public String getAdminList(HttpServletRequest request, ModelMap model) {
		List<Admin> adminList = adminService.getAllAdminList(Admin.class);
		model.put("adminList", adminList);
		return "/admin/adminList.vm";
	}

	@RequestMapping(value = "admin/updateAdmin.hel")
	public String updateAdmin(HttpServletRequest request, ModelMap model, String adminId) {

		return "/admin/updateAdmin.vm";
	}
}
