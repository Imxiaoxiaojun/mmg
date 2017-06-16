package com.mmg.webservice;

import javax.jws.WebService;

/**
 * Created by yj on 2017/6/5.
 */
@WebService
public interface HelloWorld {
    String sayHello(String name);
}
