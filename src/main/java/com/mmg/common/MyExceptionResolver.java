package com.mmg.common;

import com.mmg.util.JsonUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        MyException myException = null;

        //如果抛出的是系统自定义的异常则直接转换
        if (e instanceof MyException) {
            myException = (MyException) e;
            if (myException.getCode().equals(ErrorConstants.LOGINFAIL) || myException.getCode().equals(ErrorConstants.LOGINNULL)) {
                FlashMap flashmap = RequestContextUtils.getOutputFlashMap(request);
                flashmap.put("errorMsg", myException.getMsg());
                return new ModelAndView("redirect:/adminLogin.xhtml");
            }
        } else {
            myException = new MyException(ErrorConstants.UNDERFINE, "系统未知错误");
        }

        //向前台返回错误信息
        ModelAndView modelAndView = new ModelAndView();
        Map map = new HashMap();
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