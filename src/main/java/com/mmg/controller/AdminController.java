package com.mmg.controller;

import com.mmg.entity.admin.Admin;
import com.mmg.entity.admin.Rule;
import com.mmg.service.AdminService;
import com.mmg.util.CommonUtil;
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

    @RequestMapping(value = "adminConsole.xhtml")
    public String loadUser(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
//        String path = request.getContextPath();
//        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
//        model.put("basePath", basePath);
        adminService.loadUserToCache((String) request.getSession().getAttribute("userName"));
        return "admin/main.vm";
    }

    @RequestMapping(value = "admin/getTop.xhtml", method = RequestMethod.GET)
    public String getTop(HttpServletRequest request, ModelMap model) {
        model.put("userName", request.getSession().getAttribute("userName"));
        return "admin/top.vm";
    }

    @RequestMapping(value = "admin/getLeft.xhtml", method = RequestMethod.GET)
    public String getLeft(HttpServletRequest request, ModelMap model) {
        List<Rule> menuList = adminService.getMenuList((String) request.getSession().getAttribute("userName"));
        Map<String, List> menuMap = CommonUtil.groupMenuListByFiled(menuList);
        model.putAll(menuMap);
        return "admin/left.vm";
    }

    @RequestMapping(value = "admin/getIndex.xhtml", method = RequestMethod.GET)
    public String getIndex(HttpServletRequest request, ModelMap model) {
        Admin admin = adminService.getAdminInfo((String) request.getSession().getAttribute("userName"));
        model.put("adminInfo",admin);
        return "admin/index.vm";
    }
}
