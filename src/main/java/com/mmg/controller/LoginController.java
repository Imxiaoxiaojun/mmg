package com.mmg.controller;

import com.mmg.common.ErrorConstants;
import com.mmg.common.MyException;
import com.mmg.entity.admin.Admin;
import com.mmg.entity.common.DBLogger;
import com.mmg.service.AdminService;
import com.mmg.service.mmg.DBLoggerService;
import com.mmg.util.CommonUtil;
import com.mmg.util.DateUtil;
import com.mmg.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yj on 2017/5/6.
 */
@Controller
public class LoginController {
    @Autowired
    @Qualifier("adminService")
    private AdminService adminService;
    @Autowired
    @Qualifier("dBLoggerService")
    private DBLoggerService dBLoggerService;
    
    @RequestMapping(value = "/checkUser.xhtml", method = RequestMethod.POST)
    public String login(HttpServletRequest request, String userName, String passWord, String captchaId) throws MyException {
        if(null == request.getSession().getAttribute("verCodeCheck")) return "redirect:/adminLogin.xhtml";
        boolean verCodeCheck = ((Boolean) (request.getSession().getAttribute("verCodeCheck")));
        String epassWord = StringUtil.getMd5Stri(passWord);
        String clientIp = (String)request.getSession().getAttribute("clientIp");
        String clientMac = (String)request.getSession().getAttribute("clientMac");
        

        if (StringUtils.isBlank(userName) || StringUtils.isBlank(passWord)) {
            throw new MyException(ErrorConstants.LOGINNULL, "用户名和密码不能为空");
        }

        FlashMap flashmap = RequestContextUtils.getOutputFlashMap(request);
        flashmap.put("j_userName", userName);
        flashmap.put("captchaId", captchaId);

        if (!verCodeCheck || !adminService.checkLogin(userName, epassWord)) {
            throw new MyException(ErrorConstants.LOGINFAIL, "用户名或密码错误");
        }
      //登录成功修改最后登录时间和IP，加日志
        DBLogger dbLogger = new DBLogger(userName, clientIp, clientMac,DateUtil.getFullTime(),request.getRequestURI());
        dBLoggerService.addDBLog(dbLogger);
        Admin admin = adminService.getAdminInfo(userName);
        //获取最后登录时间和iP存放到session中
        request.getSession().setAttribute("lastLoginTime",admin.getLastLoginTime());
        request.getSession().setAttribute("lastLoginIp",admin.getLastLoginIp());
        
        admin.setLastLoginIp(clientIp);
        admin.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
        admin.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
        adminService.updateObject(admin);
        request.getSession().setAttribute("userName",userName);
        return "redirect:adminConsole.xhtml";
        
    }
}
