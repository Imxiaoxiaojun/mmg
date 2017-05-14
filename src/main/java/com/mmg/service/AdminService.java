package com.mmg.service;

import com.mmg.entity.admin.Admin;
import com.mmg.entity.admin.QuickMenu;
import com.mmg.entity.admin.Rule;

import java.util.List;

/**
 * Created by yj on 2017/5/13.
 */
public interface AdminService extends BaseService{
    boolean checkLogin(String userName,String passwd);
    List<Rule> getMenuList(String userName);
    List<QuickMenu> getQuickMenuList(Admin admin);
    Admin getAdminInfo(String userName);
    void loadUserToCache(String userName);
}
