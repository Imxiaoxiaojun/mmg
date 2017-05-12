package com.mmg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mmg.common.Captcha;
import com.mmg.common.GifCaptcha;
import com.mmg.common.VerifyCodeUtil;

/**
 * Created by yj on 2017/5/5.
 */
@Controller
public class CommonController {
    @RequestMapping(value="/adminLogin.xhtml",method= RequestMethod.GET)
    public String gotoLogin(HttpServletRequest request, ModelMap model){
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

        model.put("srcUrl",basePath);
        return "login.vm";
    }

    @RequestMapping(value="/getCaptchaId.xhtml",method= RequestMethod.GET)
    public void getCaptchaId(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");


        String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
        request.getSession().setAttribute("verCode", verifyCode.toLowerCase());

        VerifyCodeUtil.outputImage(80,28,response.getOutputStream(),verifyCode);
    }
    
    @RequestMapping(value="/getGifCaptchaId.xhtml",method= RequestMethod.GET)
    public void getGifCaptchaId(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        Captcha captcha = new GifCaptcha();

        captcha.out(response.getOutputStream());
        request.getSession().setAttribute("verifyCode",captcha.text());
//        VerifyCodeUtil.outputImage(80,28,response.getOutputStream(),verifyCode);
    }
}
