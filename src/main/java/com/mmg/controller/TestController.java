package com.mmg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mmg.entity.admin.Admin;
import com.mmg.service.BaseService;

/**
 * Created by yj on 2017/5/5.
 */
@Controller
public class TestController {
	@Autowired@Qualifier("baseDaoService")
	private BaseService baseDaoService;
	
    @RequestMapping("/test.xhtml")
    public String handleRequestInternal(HttpServletRequest req, ModelMap model, HttpServletResponse resp) throws Exception {

        List<Admin> list = baseDaoService.getAllObjects(Admin.class);
        model.put("list", list);
        return "test.vm";
    }
}
