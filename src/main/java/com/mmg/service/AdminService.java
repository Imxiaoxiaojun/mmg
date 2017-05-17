package com.mmg.service;

import com.mmg.entity.BaseObject;
import com.mmg.entity.admin.*;

import java.util.Collection;
import java.util.List;

/**
 * Created by yj on 2017/5/13.
 */
public interface AdminService extends BaseService{
    boolean checkLogin(String userName,String passwd);
    <T extends BaseObject> List<T> getAllRuleList(Class<T> clazz);
    List<QuickMenu> getQuickMenuList(Integer admin_Id);
    <T extends BaseObject> List<T> getAllRoleList(Class<T> clazz);
    <T extends BaseObject> List<T> getAllAdminList(Class<T> clazz);
    Admin getAdminInfo(String userName);
    List<Role> getRoleList(Integer admin_Id);
    List<Rule> getRuleList(Collection<Object> idList);
    <T extends BaseObject> T addObject(T entity);
}
