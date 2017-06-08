package com.mmg.webservice.impl;

import com.mmg.util.JsonUtil;
import com.mmg.webservice.HelloWorld;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yj on 2017/6/5.
 */
@WebService(endpointInterface = "com.mmg.webservice.HelloWorld")
public class HelloWroldImpl implements HelloWorld{
    public String sayHello(String name) {
        Map map = new HashMap();
        map.put("name",name);
        map.put("age","123");
        return JsonUtil.toJson(map);
    }
}
