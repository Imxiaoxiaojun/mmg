package com.mmg.service;

import java.util.Collection;
import java.util.List;

import com.mmg.common.Page;
import com.mmg.entity.BaseObject;
import com.mmg.entity.admin.Admin;
import com.mmg.entity.admin.QuickMenu;
import com.mmg.entity.admin.Role;
import com.mmg.entity.admin.Rule;
import com.mmg.service.mmg.BaseService;

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
    <T extends BaseObject> List<T> getPageList(Class<T> clazz,Page<T> page);
}
