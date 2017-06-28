package com.mmg.controller;

import com.mmg.service.mmg.BaseService;
import com.mmg.webservice.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yj on 2017/5/5.
 */
@Controller
public class TestController {
    @Autowired
    @Qualifier("baseDaoService")
    private BaseService baseDaoService;
    @Autowired
    private HelloWorld helloWorldClient;

    @RequestMapping(value = "/test/test.xhtml", method = RequestMethod.GET)
    public String test(HttpServletRequest request, ModelMap model, HttpServletResponse resp) throws Exception {
        System.err.println(helloWorldClient.sayHello("zhuyajun"));
        return "test.vm";
//        return "redirect:/adminLogin.xhtml";
    }
}
