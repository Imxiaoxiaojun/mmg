package com.mmg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yj on 2017/5/5.
 */
@Controller
public class TestController {
    @RequestMapping("/admin/test.xhtml")
    public String handleRequestInternal(HttpServletRequest req, ModelMap model, HttpServletResponse resp) throws Exception {
        System.out.println("------------ enter VelocityController");

        List<String> list = new ArrayList<String>();
        list.add("one");
        list.add("two");
        list.add("three");
        model.put("list", list);
        return "test.vm";
    }
}
