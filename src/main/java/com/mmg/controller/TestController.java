package com.mmg.controller;

import com.mmg.entity.admin.Admin;
import com.mmg.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by yj on 2017/5/5.
 */
@Controller
public class TestController {
    @Autowired
    @Qualifier("baseDaoService")
    private BaseService baseDaoService;

    @RequestMapping(value = "/test/test.xhtml", method = RequestMethod.GET)
    public String test(HttpServletRequest request, ModelMap model, HttpServletResponse resp) throws Exception {

        return "test.vm";
//        return "redirect:/adminLogin.xhtml";
    }
}
