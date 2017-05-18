package com.mmg.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yj on 2017/5/8.
 */
public class LoginInterceptor implements HandlerInterceptor {
    private Log logger = LogFactory.getLog(this.getClass());
    private List<String> passUrl;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	 String path = request.getContextPath();
         String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    	HttpSession session = request.getSession();
//    	session.setAttribute("basePath", basePath);
//    	session.setAttribute("basePath1", "/mmg");
    	
    	
        String url = request.getRequestURI();
        for(Iterator<String> it = passUrl.iterator(); it.hasNext();){
            if(url.indexOf(it.next())>=0)return true;
        }
        
        String username = (String)session.getAttribute("userName");
        if(username != null){
            return true;
        }

        //不符合条件的，跳转到登录界面
        response.sendRedirect ("adminLogin.xhtml");

        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.debug("aaa");
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.debug("aaa");
    }

    public void setPassUrl(List<String> passUrl) {
        this.passUrl = passUrl;
    }
}
