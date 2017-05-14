package com.mmg.util;

import com.mmg.entity.admin.Rule;

import java.util.*;

/**
 * Created by yj on 2017/5/13.
 */
public class CommonUtil {
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
}
