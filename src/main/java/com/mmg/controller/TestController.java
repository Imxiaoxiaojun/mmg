package com.mmg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yj on 2017/5/5.
 */
@Controller
public class TestController extends AbstractController {
    @Override
    @RequestMapping("/admin/test.xhtml")
    protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("------------ enter VelocityController");

        List<String> list = new ArrayList<String>();
        list.add("one");
        list.add("two");
        list.add("three");
        return new ModelAndView("page/test", "list", list);
    }
}
