package com.mmg.util;

import com.mmg.entity.admin.Admin;
import com.mmg.entity.admin.Rule;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yj on 2017/5/13.
 */
public class CommonUtil {
    private static final Log log = LogFactory.getLog(CommonUtil.class);
    public static Map<String,List> groupMenuListByFiled(List<Rule> list){
        Map<String,List> map = new HashMap<String, List>();
        if(null == list || list.size() == 0) return map;
        for(Rule rule : list){
            List tempList = map.get("menuList"+rule.getLevel());
            if (tempList == null) {
                tempList = new ArrayList();
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
     * @param fieldName 要查询的字段名称，如果uniqueClassName不为空，则送uniqueClassName中的字段名 "person_id" or "woman_id"
     *
     * @return
     */
    public static List getFieldList(List<?> list, Class<?> clazz ,String uniqueClassName, String fieldName){
        List filedList = new ArrayList();
        try{
            if(!StringUtils.isBlank(uniqueClassName)){
                Field uniqueClassField = clazz.getDeclaredField(uniqueClassName);
                uniqueClassField.setAccessible(true);
                for(Object object : list){
                    Object uniqueObj = uniqueClassField.get(object);
                    Class uniqueClass = uniqueObj.getClass();
                    Field uniField = uniqueClass.getDeclaredField(fieldName);
                    uniField.setAccessible(true);
                    Object uniFieldVal = uniField.get(uniqueObj);
                    filedList.add(uniFieldVal);
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

    public static void main(String[] args) throws Exception{
        List<Admin> list = new ArrayList();
        Admin admin = new Admin();
        admin.setId(1);
        admin.setAdminId("admin");
        admin.setPassword("123123");
        admin.setAdminName("管理员");
        Admin admin2= new Admin();
        admin2.setId(1);
        admin2.setAdminId("admin2");
        admin2.setPassword("321321");
        admin2.setAdminName("管理员2");
        list.add(admin);
        list.add(admin2);
        List fieldList = getFieldList(list,Admin.class,"","id");
        for(Object object:fieldList){
            System.out.println(object);
        }
    }

    public static List<List<Field>> getBomFields1(List<Field> chain, Field[] fields) {
        List<List<Field>> result = new ArrayList<List<Field>>();
        for (Field field : fields) {
            Class<?> fieldClass = field.getType();
            if (fieldClass.isPrimitive() || fieldClass.getName().startsWith("java.lang") || fieldClass.getName().startsWith("java.util.Date") || fieldClass.getName().startsWith("javax") || fieldClass.getName().startsWith("com.sun") || fieldClass.getName().startsWith("sun") || fieldClass.getName().startsWith("boolean") || fieldClass.getName().startsWith("double") || fieldClass.getName().startsWith("int")) {
                List<Field> endChain = new ArrayList<Field>(chain);
                endChain.add(field);
                result.add(endChain);
                continue;
            } else {
                if (fieldClass.isAssignableFrom(List.class)) {
                    Type fc = field.getGenericType(); // 关键的地方，如果是List类型，得到其Generic的类型
                    if (fc instanceof ParameterizedType) // 【3】如果是泛型参数的类型
                    {
                        ParameterizedType pt = (ParameterizedType) fc;
                        Class genericClazz = (Class) pt.getActualTypeArguments()[0]; // 【4】
                        if (genericClazz.getName().startsWith("java.lang") //设置list的终止类型
                                || genericClazz.getName().startsWith("java.util.Date")
                                || genericClazz.getName().startsWith("javax")
                                || genericClazz.getName().startsWith("com.sun")
                                || genericClazz.getName().startsWith("sun")
                                || genericClazz.getName().startsWith("boolean")
                                || genericClazz.getName().startsWith("double")
                                || genericClazz.getName().startsWith("int")) {
                            continue;
                        }
                        System.out.println(genericClazz);//得到泛型里的class类型对象。
                        List<Field> thisChain = new ArrayList<Field>(chain); //
                        System.out.println(chain);
                        thisChain.add(field); //!!
                        result.addAll(getBomFields1(new ArrayList<Field>(thisChain), genericClazz.getDeclaredFields()));
                    }
                } else {
                    List<Field> thisChain = new ArrayList<Field>(chain);
                    thisChain.add(field);
                    result.addAll(getBomFields1(new ArrayList<Field>(thisChain),
                            fieldClass.getDeclaredFields()));
                }
            }
        }
        return result;
    }
}
