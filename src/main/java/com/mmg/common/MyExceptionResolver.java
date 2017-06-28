package com.mmg.common;

import com.mmg.entity.common.DBLogger;
import com.mmg.service.mmg.DBLoggerService;
import com.mmg.util.JsonUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yj on 2017/5/13.
 */
public class MyExceptionResolver implements HandlerExceptionResolver {
    private String viewName;
    private Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    @Qualifier("dBLoggerService")
    private DBLoggerService dBLoggerService;

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        MyException myException = null;
        String clientIp = (String)request.getSession().getAttribute("clientIp");
        String clientMac = (String)request.getSession().getAttribute("clientMac");
        
        //如果抛出的是系统自定义的异常则直接转换
        if (e instanceof MyException) {
            myException = (MyException) e;
//            if (myException.getCode().equals(ErrorConstants.LOGINFAIL) || myException.getCode().equals(ErrorConstants.LOGINNULL)) {
            if(request.getRequestURI().equals("checkUser.xhtml")){
                FlashMap flashmap = RequestContextUtils.getOutputFlashMap(request);
                flashmap.put("errorMsg", myException.getMsg());
//                flashmap.put("errorMsg","9");
                return new ModelAndView("redirect:/adminLogin.xhtml");
            }
        } else {
        	logger.error("系统错误"+e);
            myException = new MyException(ErrorConstants.UNDERFINE, "系统未知错误");
        }

        //向前台返回错误信息
        ModelAndView modelAndView = new ModelAndView();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("errCode", myException.getCode());
        map.put("errMsg", myException.getMsg());
        String jsonStr = JsonUtil.toJson(map);
        modelAndView.addObject("jsonStr", jsonStr);
        modelAndView.setViewName(viewName);
        response.setStatus(Integer.parseInt(myException.getCode()));
        return modelAndView;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
}
