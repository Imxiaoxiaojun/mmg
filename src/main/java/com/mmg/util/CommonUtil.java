package com.mmg.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mmg.entity.admin.Rule;


/**
 * Created by yj on 2017/5/13.
 */
public class CommonUtil {
    private static final Log log = LogFactory.getLog(CommonUtil.class);
    
    public static void mapToBean(Map<String, Object> map, Object obj){
    	if (map == null || obj == null) {  
            return;  
        }  
        try {  
            BeanUtils.populate(obj, map);  
        } catch (Exception e) {  
            System.out.println("transMap2Bean2 Error " + e);  
        }  
    }
    
    public static Map<?,?> beanToMap(Object obj){
    	if(obj == null){  
            return null;  
        }          
        Map<String, Object> map = new HashMap<String, Object>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
  
                // 过滤class属性  
                if (!key.equals("class")) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(obj);  
  
                    map.put(key, value);  
                }  
  
            }  
        } catch (Exception e) {  
            System.out.println("transBean2Map Error " + e);  
        }  
  
        return map;  
  
    }  
    
    public static Map<String,List<Rule>> groupMenuListByFiled(List<Rule> list){
        Map<String,List<Rule>> map = new HashMap<String, List<Rule>>();
        if(null == list || list.size() == 0) return map;
        for(Rule rule : list){
            List<Rule> tempList = map.get("menuList"+rule.getLevel());
            if (tempList == null) {
                tempList = new ArrayList<Rule>();
                tempList.add(rule);
                map.put("menuList"+rule.getLevel(), tempList);
            }
            else {
                tempList.add(rule);
            }
        }
        return map;
    }

    /***
     * 查询list对象的字段list
     * @param list
     * @param clazz list对象class
     * @param uniqueClassName 如果查询的字段在别类中 传字段所在类的类名 否则送空字符“”
     *                for example      public class Person{private Integer person_id,private String person_name,private Woman woman}
     *                                 public class Woman{private Integer woman_id,private String woman_name}
     *                "woman" or ""
     * @param fieldName 要查询的字段名称，如果uniqueClassName不为空，则送uniqueClassName中的字段名 "person_id" or "woman_id" ,如果为空，将直接返回引用的对象
     *
     * @return
     */

    public static List<Object> getFieldList(List<?> list, Class<?> clazz ,String uniqueClassName, String fieldName){
		List<Object> filedList = new ArrayList<Object>();
        try{
            if(!StringUtils.isBlank(uniqueClassName)){
                Field uniqueClassField = clazz.getDeclaredField(uniqueClassName);
                uniqueClassField.setAccessible(true);
                for(Object object : list){
                    Object uniqueObj = uniqueClassField.get(object);
                    Class<?> uniqueClass = uniqueObj.getClass();
                    if(!StringUtils.isBlank(fieldName)){
                        Field uniField = uniqueClass.getDeclaredField(fieldName);
                        uniField.setAccessible(true);
                        Object uniFieldVal = uniField.get(uniqueObj);
                        filedList.add(uniFieldVal);
                    }else {
                        filedList.add(uniqueObj);
                    }
                }
            }else{
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                for(Object obj : list){
                    Object val =field.get(obj);
                    filedList.add(val);
                }
            }
        }catch (NoSuchFieldException e){
            log.error(e.getMessage());
            e.printStackTrace();
        }catch (IllegalAccessException e1){
            log.error(e1.getMessage());
            e1.printStackTrace();
        }
        return filedList;
    }
 
    public static String getIp(HttpServletRequest request){
    	String ip = request.getHeader("x-forwarded-for");  
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }  
//        ip = ip.equals("0:0:0:0:0:0:0:1")||ip.equals("127.0.0.1")?"101.95.157.134":ip;
        return ip;
    }
    
    public static String getMACAddress() {
        String mac = null;
        try {
        	Process pro = Runtime.getRuntime().exec("cmd.exe /c ipconfig/all");
 
            InputStream is = pro.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"GBK"));
            String message = br.readLine();
 
            int index = -1;
            while (message != null) {
                if ((index = message.indexOf("Physical Address")) > 0||message.indexOf("物理地址") > 0) {
                    mac = message.substring(index + 36).trim();
                    break;
                }
                message = br.readLine();
            }
            br.close();
            pro.destroy();
        } catch (IOException e) {
            System.out.println("Can't get mac address!");
            return null;
        }
        return mac;
    }

	public static String getClientMac(String ipAddress){
		// TODO Auto-generated method stub
		String macAddress = "";
		final String LOOPBACK_ADDRESS1 = "127.0.0.1";
		final String LOOPBACK_ADDRESS2 = "0:0:0:0:0:0:0:1";
		// 如果为127.0.0.1,则获取本地MAC地址。
		if (LOOPBACK_ADDRESS1.equals(ipAddress)||LOOPBACK_ADDRESS2.equals(ipAddress)) {
			InetAddress inetAddress;
			byte[] mac;
			try {
				inetAddress = InetAddress.getLocalHost();
				mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
				// 貌似此方法需要JDK1.6。
				// 下面代码是把mac地址拼装成String
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < mac.length; i++) {
					if (i != 0) {
						sb.append("-");
					}
					// mac[i] & 0xFF 是为了把byte转化为正整数
					String s = Integer.toHexString(mac[i] & 0xFF);
					sb.append(s.length() == 1 ? 0 + s : s);
				}
				macAddress = sb.toString().trim().toUpperCase();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (SocketException e) {
				e.printStackTrace();
			}
			
			// 把字符串所有小写字母改为大写成为正规的mac地址并返回
		} else{
			macAddress = getMACAddress();
		}
		return macAddress;
	}

    public static String getTransId(HttpServletRequest request){
    	String url = request.getRequestURI();
    	return url;
    }
    public static void main(String[] args) {      
        System.out.println(getClientMac("192.168.12.139"));
      }

}
