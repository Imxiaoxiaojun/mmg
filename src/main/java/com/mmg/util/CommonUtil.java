package com.mmg.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public static String callCmd(String[] cmd) { 
        String result = ""; 
        String line = ""; 
        try { 
          Process proc = Runtime.getRuntime().exec(cmd); 
          InputStreamReader is = new InputStreamReader(proc.getInputStream()); 
          BufferedReader br = new BufferedReader (is); 
          while ((line = br.readLine ()) != null) { 
          result += line; 
          } 
        } 
        catch(Exception e) { 
          e.printStackTrace(); 
        } 
        return result; 
      }
    public static String callCmd(String[] cmd,String[] another) { 
        String result = ""; 
        String line = ""; 
        try { 
          Runtime rt = Runtime.getRuntime(); 
          Process proc = rt.exec(cmd); 
          proc.waitFor(); //已经执行完第一个命令，准备执行第二个命令 
          proc = rt.exec(another); 
          InputStreamReader is = new InputStreamReader(proc.getInputStream()); 
          BufferedReader br = new BufferedReader (is); 
          while ((line = br.readLine ()) != null) { 
            result += line; 
          } 
        } 
        catch(Exception e) { 
          e.printStackTrace(); 
        } 
        return result; 
      }
    public static String filterMacAddress(final String ip, final String sourceString,final String macSeparator) { 
        String result = ""; 
        String regExp = "((([0-9,A-F,a-f]{1,2}" + macSeparator + "){1,5})[0-9,A-F,a-f]{1,2})"; 
        Pattern pattern = Pattern.compile(regExp); 
        Matcher matcher = pattern.matcher(sourceString); 
        while(matcher.find()){ 
          result = matcher.group(1); 
          if(sourceString.indexOf(ip) <= sourceString.lastIndexOf(matcher.group(1))) { 
            break; //如果有多个IP,只匹配本IP对应的Mac. 
          } 
        }
      
        return result; 
      }
    public static String getMacInWindows(final String ip){ 
        String result = ""; 
        String[] cmd = { 
            "cmd", 
            "/c", 
            "ping " + ip 
            }; 
        String[] another = { 
            "cmd", 
            "/c", 
            "arp -a"
            }; 
      
        String cmdResult = callCmd(cmd,another); 
        result = filterMacAddress(ip,cmdResult,"-"); 
      
        return result; 
      } 
    
    public static String getMacInLinux(final String ip){ 
        String result = ""; 
        String[] cmd = { 
            "/bin/sh", 
            "-c", 
            "ping " + ip + " -c 2 && arp -a"
            }; 
        String cmdResult = callCmd(cmd); 
        result = filterMacAddress(ip,cmdResult,":"); 
      
        return result; 
      } 
    public static String getMacAddress(String ip){
        String macAddress = "";
        macAddress = getMacInWindows(ip).trim();
        if(macAddress==null||"".equals(macAddress)){
          macAddress = getMacInLinux(ip).trim();
        }
        return macAddress;
      }

    public static void main(String[] args) {      
        System.out.println(getMacAddress("220.181.111.148"));
      }

}
