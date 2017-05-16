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
    List<Role> getRoleList(Integer admin_Id);
    List<Rule> getRuleList(Collection<Object> idList);
}
