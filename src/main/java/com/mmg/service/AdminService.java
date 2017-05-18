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
    List<Rule> getAllRuleList(Class<Rule> clazz );
    List<QuickMenu> getQuickMenuList(Integer admin_Id);
    List<Role> getAllRoleList(Class<Role> clazz );
    List<Admin> getAllAdminList(Class<Admin> clazz);
    Admin getAdminInfo(String userName);
    List<Role> getRoleList(Integer admin_Id);
    List<Rule> getRuleList(Collection<Object> idList);
    <T extends BaseObject> T addObject(T entity);
    <T extends BaseObject> T removeObject(T entity);
    <T extends BaseObject> T updateObject(T entity);
}
