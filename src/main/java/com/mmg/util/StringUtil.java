package com.mmg.util;

import java.security.MessageDigest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Encoder;

/**
 * Created by yj on 2017/5/13.
 */
public class StringUtil {
    private final static  Log logger = LogFactory.getLog(StringUtil.class);
    @SuppressWarnings("restriction")
	public static String getMd5Stri(String str){
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            return base64en.encode(md5.digest(str.getBytes("utf-8")));
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return str;
    }
}
