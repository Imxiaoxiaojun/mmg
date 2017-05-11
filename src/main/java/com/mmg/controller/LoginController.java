package com.mmg.controller;

import com.mmg.service.IAdminService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yj on 2017/5/6.
 */
@Controller
public class LoginController {
    @Autowired
    private IAdminService adminService;

    @RequestMapping(value = "/admin/adminConsole.xhtml",method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model, String userName, String passWord,String captchaId){
        if(StringUtils.isBlank(userName) || StringUtils.isBlank(passWord)){
            //TODO
            return null;
        }
        FlashMap flashmap = RequestContextUtils.getOutputFlashMap(request);
        flashmap.put("j_userName",userName);
        flashmap.put("captchaId",captchaId);
        if(adminService.checkLogin(userName,passWord)){
            return "admin/main.vm";
//            return "index.html";
        }

        return  "redirect:/adminLogin.xhtml";
    }
}
