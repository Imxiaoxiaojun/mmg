package com.mmg.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mmg.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mmg.common.Constants;
import com.mmg.common.Page;
import com.mmg.entity.admin.Admin;
import com.mmg.entity.admin.Role;
import com.mmg.entity.admin.Rule;
import com.mmg.entity.common.DBLogger;
import com.mmg.vo.IpAddress;
import com.mmg.vo.Weather;
import com.mmg.vo.Weather24H;
import com.mmg.service.AdminService;
import com.mmg.service.mmg.DBLoggerService;
import com.mmg.service.mmg.IpService;
import com.mmg.service.mmg.WeatherService;
import com.mmg.util.CipherUtil;
import com.mmg.util.CommonUtil;
import com.mmg.util.DateUtil;

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
    @Autowired
    @Qualifier("dBLoggerService")
    private DBLoggerService dBLoggerService;
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "adminConsole.xhtml")
	public String getMain(HttpServletRequest request, HttpServletResponse response) {
		String userId =  (String) request.getSession().getAttribute(Constants.CKUSERID);
		//
		if(StringUtils.isNotBlank(userId))refreshCookie(request,response);

		loadUser(request,response);

		String ip = (String) request.getSession().getAttribute("clientIp");
		IpAddress ipAddress = ipService.queryLocalByIp(ip);

		String area = "";
		if (null != ipAddress) {
			area = ipAddress.getRegion();
		}
		Weather weather = weatherService.get24HourInfo(area);

		if (weather == null || weather.getHourList() == null || weather.getHourList().size() <= 0)
			return "admin/main.vm";

		Map<String, Object> map = (Map<String, Object>) weather.getHourList().get(0);
		Weather24H weather24 = new Weather24H();
		CommonUtil.mapToBean(map, weather24);
		String weatherTime = weather24.getTime();

		if (weatherTime == null)
			return "admin/main.vm";
		if (DateUtil.isDay(weatherTime.substring(weatherTime.length() - 4, weatherTime.length()))) {
			request.getSession().setAttribute("dayCode", weather24.getWeather_code());
		} else {
			request.getSession().setAttribute("nightCode", weather24.getWeather_code());
		}
		request.getSession().setAttribute("weather", weather24);
		return "admin/main.vm";
	}

	@RequestMapping(value = "admin/getTop.xhtml", method = RequestMethod.GET)
	public String getTop(HttpServletRequest request, ModelMap model) {
		model.put("userName", request.getSession().getAttribute("userName"));
		return "admin/top.vm";
	}

	@RequestMapping(value = "admin/getLeft.xhtml", method = RequestMethod.GET)
	public String getLeft(HttpServletRequest request, ModelMap model) {
		Admin admin = adminService.getAdminInfo((String) request.getSession().getAttribute("userName"));
		List<Role> roleList = adminService.getRoleList(admin.getId());
		List<Object> roleIdlist = CommonUtil.getFieldList(roleList, Role.class, "", "id");
		List<Rule> ruleList = adminService.getRuleList(roleIdlist);
		Map<String, List<Rule>> menuMap = CommonUtil.groupMenuListByFiled(ruleList);
		model.putAll(menuMap);
		return "admin/left.vm";
	}

	@RequestMapping(value = "admin/getIndex.xhtml", method = RequestMethod.GET)
	public String getIndex(HttpServletRequest request, ModelMap model) {
		Admin admin = adminService.getAdminInfo((String) request.getSession().getAttribute("userName"));
		model.put("dayCode", request.getSession().getAttribute("dayCode"));
		model.put("nightCode", request.getSession().getAttribute("nightCode"));
		model.put("weather", request.getSession().getAttribute("weather"));
		model.put("adminInfo", admin);

		model.put("lastLoginTime", request.getSession().getAttribute("lastLoginTime"));
		model.put("lastLoginIp", request.getSession().getAttribute("lastLoginIp"));
		model.put("lastLoginLocale",
				ipService.queryLocalByIp((String) request.getSession().getAttribute("lastLoginIp")).getRegion());
		return "admin/index.vm";
	}

	@RequestMapping(value = "admin/admin_logout.xhtml", method = RequestMethod.GET)
	public String adminLogout(HttpServletRequest request, ModelMap model) {
		request.getSession().removeAttribute(Constants.USERNAME);
		return "redirect:/adminLogin.xhtml";
	}

	@RequestMapping(value = "admin/getRuleList.xhtml", method = RequestMethod.GET)
	public String getRuleList(HttpServletRequest request, Integer curpage, Integer pagesize, ModelMap model) {
		// List<Rule> ruleList = adminService.getAllRuleList(Rule.class);
		/*
		 * model.put("ruleList",ruleList); model.put("count",150);
		 * model.put("pagesize",15);
		 */
		int count = adminService.getObjectCount(Rule.class);
		Page<Rule> page = new Page<Rule>(count, curpage, pagesize);
		List<Rule> ruleList = adminService.getPageList(Rule.class, page);
		page.setPageList(ruleList);
		model.put("pageInfo", page);
		return "/admin/ruleList.vm";
	}

	@RequestMapping(value = "admin/getRoleList.xhtml", method = RequestMethod.GET)
	public String getRoleList(HttpServletRequest request, Integer curpage, Integer pagesize, ModelMap model) {
		int count = adminService.getObjectCount(Role.class);
		Page<Role> page = new Page<Role>(count, curpage, pagesize);
		List<Role> ruleList = adminService.getPageList(Role.class, page);
		page.setPageList(ruleList);
		model.put("pageInfo", page);
		return "/admin/roleList.vm";
	}

	@RequestMapping(value = "admin/getAdminList.xhtml", method = RequestMethod.GET)
	public String getAdminList(HttpServletRequest request, Integer curpage, Integer pagesize, ModelMap model) {
		int count = adminService.getObjectCount(Admin.class);
		Page<Admin> page = new Page<Admin>(count, curpage, pagesize);
		List<Admin> ruleList = adminService.getPageList(Admin.class, page);
		page.setPageList(ruleList);
		model.put("pageInfo", page);
		return "/admin/adminList.vm";
	}

	@RequestMapping(value = "admin/showAdmin.xhtml")
	public String showAdmin(HttpServletRequest request, ModelMap model, Integer adminId) {
		if (adminId == null)
			return "/admin/adminInfo.vm";
		Admin admin = adminService.getObject(Admin.class, adminId);
		if (admin != null)
			model.put("admin", admin);
		return "/admin/adminInfo.vm";
	}

	@RequestMapping(value = "admin/showRule.xhtml")
	public String showRule(HttpServletRequest request, ModelMap model, Integer ruleId) {
		if (ruleId == null)
			return "/admin/showRule.vm";
		Rule rule = adminService.getObject(Rule.class, ruleId);
		if (rule != null)
			model.put("rule", rule);
		return "/admin/showRule.vm";
	}

	@RequestMapping(value = "admin/showRole.xhtml")
	public String showRole(HttpServletRequest request, ModelMap model, Integer roleId) {
		if (roleId == null)
			return "/admin/showRole.vm";
		Role role = adminService.getObject(Role.class, roleId);
		if (role != null)
			model.put("role", role);
		return "/admin/showRole.vm";
	}
	private void loadUser(HttpServletRequest request , HttpServletResponse response){
		String clientIp = CommonUtil.getIp(request);
		String userName = (String)request.getSession().getAttribute(Constants.USERNAME);

		Admin admin = adminService.getAdminInfo(userName);
		//获取最后登录时间和iP存放到session中
		request.getSession().setAttribute(Constants.LASTLOGINTIME,admin.getLastLoginTime());
		request.getSession().setAttribute(Constants.LASTLOGINIP,admin.getLastLoginIp());

		admin.setLastLoginIp(clientIp);
		admin.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
		admin.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
		adminService.updateObject(admin);
		request.getSession().setAttribute("userName",userName);
	}
	private void refreshCookie(HttpServletRequest request, HttpServletResponse response){
		String clientMac = (String)request.getSession().getAttribute("clientMac");
		String clientIp = CommonUtil.getIp(request);
		String ckUserId =  (String) request.getSession().getAttribute(Constants.CKUSERID);
		String endTime = String.valueOf(System.currentTimeMillis() + Constants.CKVALIDTIME * 1000);
		String original = clientIp+","+ckUserId+","+endTime;
		String ckValue = CipherUtil.threeDesEncrypt(original);
		Cookie cookie = new Cookie(Constants.COOKIENAME,ckValue);
		cookie.setMaxAge(Constants.CKVALIDTIME);
		cookie.setPath("/");
		response.addCookie(cookie);
		request.getSession().removeAttribute(Constants.CKUSERID);
		request.getSession().setAttribute(Constants.USERNAME,ckUserId);

		DBLogger dbLogger = new DBLogger(ckUserId, clientIp, clientMac,DateUtil.getFullTime(),request.getRequestURI());
		dBLoggerService.addDBLog(dbLogger);
	}

	public static void main(String[] args) {
		System.out.println(StringUtil.getMd5Stri("111"));
	}
}
