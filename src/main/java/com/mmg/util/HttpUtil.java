package com.mmg.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by yj on 2017/6/29.
 */
public class HttpUtil {
    private static final Log logger = LogFactory.getLog(HttpUtil.class);
    public static Cookie getCookie(HttpServletRequest request){
        Cookie[] cookie = request.getCookies();
        return cookie[0];
    }
}
