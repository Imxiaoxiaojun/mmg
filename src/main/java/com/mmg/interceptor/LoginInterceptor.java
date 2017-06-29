package com.mmg.interceptor;

import com.mmg.common.Constants;
import com.mmg.common.ErrorConstants;
import com.mmg.common.MyException;
import com.mmg.util.CipherUtil;
import com.mmg.util.CommonUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yj on 2017/5/8.
 */
public class LoginInterceptor implements HandlerInterceptor {
    private Log logger = LogFactory.getLog(this.getClass());
    private List<String> passUrl;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();

        String url = request.getRequestURI();
        for(Iterator<String> it = passUrl.iterator(); it.hasNext();){
            if(url.indexOf(it.next())>=0)return true;
        }
        
        if(session.getAttribute(Constants.USERNAME) != null || session.getAttribute(Constants.CKUSERID) != null){
            return true;
        }

        if(checkCookie(cookies,request))return true;

        //不符合条件的，跳转到登录界面
        response.sendRedirect ("adminLogin.xhtml");

        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
    	logger.info(request.getSession().getAttribute("clientIp")+"-------posthandle------"+request.getRequestURI());
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
       logger.info(request.getSession().getAttribute("clientIp")+"---completion----------"+request.getRequestURI());
    }

    public void setPassUrl(List<String> passUrl) {
        this.passUrl = passUrl;
    }

    public boolean checkCookie(Cookie[] cookies,HttpServletRequest request) throws MyException{
        try {
            String ckValue = "";
            if (cookies != null && cookies.length < 1) return false;
            for (Cookie cookie : cookies) {
                if (cookie != null && cookie.getName().equals(Constants.COOKIENAME)) {
                    ckValue = cookie.getValue();
                }
            }
            if (ckValue == null) return false;
            String[] values = CipherUtil.threeDesDecrypt(ckValue).split(",");
            if (values == null || values.length <= 1) return false;
            String ckIp = values[0];
            String ckUserId = values[1];
            String ckEndTime = values[2];
            if (!StringUtils.equals(ckIp, CommonUtil.getIp(request))) return false;
            if (StringUtils.isBlank(ckEndTime)) return false;
            Timestamp endTime = new Timestamp(Long.valueOf(ckEndTime));
            Timestamp nowTime = new Timestamp(System.currentTimeMillis());
            if (endTime.compareTo(nowTime) > 0) {
                request.getSession().setAttribute(Constants.CKUSERID, ckUserId);
                return true;
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new MyException(ErrorConstants.COOKIEERROR,"cookie信息异常，请重新登陆");
        }
            return false;
    }
}
