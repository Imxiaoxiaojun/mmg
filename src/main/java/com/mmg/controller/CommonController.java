package com.mmg.controller;

import com.mmg.common.MyException;
import com.mmg.common.VerifyCodeUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        model.put("basePath", basePath);
        return "login.vm";
    }

    @RequestMapping(value = "/getCaptchaId.xhtml", method = RequestMethod.GET)
    public void getCaptchaId(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");


        String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
        request.getSession().setAttribute("verCode", verifyCode.toLowerCase());
        VerifyCodeUtil.outputImage(80, 28, response.getOutputStream(), verifyCode);
    }

    @RequestMapping(value = "/checkCaptchaId.xhtml", method = RequestMethod.GET)
    public void checkCaptchaId(HttpServletRequest request, HttpServletResponse response, String captchaId, ModelMap model) throws MyException {
        String sverCode = (String) request.getSession().getAttribute("verCode");
        if (StringUtils.isBlank(captchaId) || !captchaId.toLowerCase().equals(sverCode.toLowerCase())) {
            //throw new MyException(ErrorConstants.VERCODEFAIL, "验证码错误");
        }
        request.getSession().setAttribute("verCodeCheck", true);
    }
}
