package com.mmg.controller;

import com.mmg.common.CountVerifyCodeTool;
import com.mmg.common.MyException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by yj on 2017/5/5.
 */
@Controller
public class CommonController {
    private Log logger = LogFactory.getLog(this.getClass());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String toLogin() throws MyException {
        return "redirect:/adminLogin.xhtml";
    }

    @RequestMapping(value = "/adminLogin.xhtml", method = RequestMethod.GET)
    public String gotoLogin(HttpServletRequest request, ModelMap model) throws MyException {
    	String ip = request.getHeader("x-forwarded-for");  
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }  
        ip = ip.equals("0:0:0:0:0:0:0:1")||ip.equals("127.0.0.1")?"101.95.157.134":ip;
        logger.info(ip);  
        request.getSession().setAttribute("clientIp", ip);
        return "login1.vm";
    }

    @RequestMapping(value = "/getCaptchaId.xhtml", method = RequestMethod.GET)
    public void getCaptchaId(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        response.setCharacterEncoding("UTF-8");
        try {
            CountVerifyCodeTool tool = new CountVerifyCodeTool();
            BufferedImage image = tool.drawVerificationCodeImage();
            int result = tool.getXyresult();
            request.getSession().setAttribute("verCode",result);
            ImageIO.write(image,"PNG", response.getOutputStream());
        }catch (IOException e){
            logger.error(e.getMessage());
        }

//        String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
//        request.getSession().setAttribute("verCode", verifyCode.toLowerCase());
//        VerifyCodeUtil.outputImage(80, 40, response.getOutputStream(), verifyCode);
    }

    @RequestMapping(value = "/checkCaptchaId.xhtml", method = RequestMethod.GET)
    public void checkCaptchaId(HttpServletRequest request, HttpServletResponse response, String captchaId, ModelMap model) throws MyException {
        String sverCode = String.valueOf(request.getSession().getAttribute("verCode"));
        if (StringUtils.isBlank(captchaId) || !captchaId.toLowerCase().equals(sverCode.toLowerCase())) {
            //throw new MyException(ErrorConstants.VERCODEFAIL, "验证码错误");
        }
        request.getSession().setAttribute("verCodeCheck", true);
    }
}
