package com.mmg.service;

import com.mmg.entity.admin.*;

import java.util.Collection;
import java.util.List;

/**
 * Created by yj on 2017/5/13.
 */
public interface AdminService extends BaseService{
    boolean checkLogin(String userName,String passwd);
    List<Rule> getAllRuleList();
    List<QuickMenu> getQuickMenuList(Integer admin_Id);
    Admin getAdminInfo(String userName);
    void loadUserToCache(String userName);
    List<User_Role> getUser2RoleList(Integer admin_Id);
    List<Role_Rule> getRole2RuleList(Collection<Integer> idList);
}
