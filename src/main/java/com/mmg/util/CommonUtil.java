package com.mmg.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mmg.entity.admin.Rule;

/**
 * Created by yj on 2017/5/13.
 */
public class CommonUtil {
    private static final Log log = LogFactory.getLog(CommonUtil.class);
    public static Map<String,List<Rule>> groupMenuListByFiled(List<Rule> list){
        Map<String,List<Rule>> map = new HashMap<String, List<Rule>>();
        if(null == list || list.size() == 0) return map;
        for(Rule rule : list){
            List<Rule> tempList = map.get("menuList"+rule.getLevel());
            if (tempList == null) {
                tempList = new ArrayList<>();
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
		List<Object> filedList = new ArrayList<>();
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

}
