package com.mmg.service.impl;

import com.mmg.common.CacheManager;
import com.mmg.dao.BaseDao;
import com.mmg.entity.admin.Admin;
import com.mmg.entity.admin.QuickMenu;
import com.mmg.entity.admin.Role_Rule;
import com.mmg.entity.admin.Rule;
import com.mmg.service.AdminService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yj on 2017/5/6.
 */
@Transactional
@Service("adminService")
public class AdminServiceImpl extends BaseServiceImpl implements AdminService{
    @Autowired@Qualifier("baseDao")
    private BaseDao baseDao;

    public Admin getAdminInfo(String userName) {
        Admin adminInfo = (Admin) CacheManager.getCacheInfo("adminInfo");
        if(null != adminInfo) {
            return adminInfo;
        }else {
            DetachedCriteria criteria = DetachedCriteria.forClass(Admin.class);
            criteria.add(Restrictions.eq("userName", userName));
            List<Admin> adminList = (List<Admin>)baseDao.findByCriteria(criteria);
            return adminList.get(0);
        }
    }

    public List<QuickMenu> getQuickMenuList(Admin admin) {
        return null;
    }
    public void loadUserToCache(String userName) {

        DetachedCriteria criteria = DetachedCriteria.forClass(Admin.class);
        criteria.add(Restrictions.eq("userName", userName));
        List<Admin> adminList = (List<Admin>)baseDao.findByCriteria(criteria);
        if(null == adminList || adminList.size() <= 0)return;
        //缓存用户信息
        CacheManager.putCache("adminInfo",adminList.get(0));

        Integer roleId = adminList.get(0).getRole().getRoleId();
        DetachedCriteria role_rule = DetachedCriteria.forClass(Role_Rule.class);
        role_rule.add(Restrictions.ge("role.roleId",roleId));
        List<Role_Rule> roleRuleList = (List<Role_Rule>)baseDao.findByCriteria(role_rule);
        if(roleRuleList.size() <=0 )return;

        for(Iterator<Role_Rule> it = roleRuleList.iterator();it.hasNext();){
            Integer ruleId = it.next().getRule().getRuleId();
            Rule rule = baseDao.getObject(Rule.class,ruleId);
            if(null != rule){
                CacheManager.putCache("menu"+rule.getRuleId(),rule);//缓存用户对应的权限
            }
        }
        DetachedCriteria quickMenu = DetachedCriteria.forClass(QuickMenu.class);
        quickMenu.add(Restrictions.eq("admin.adminId",adminList.get(0).getAdminId()));
        List<QuickMenu> quickMenuList = (List<QuickMenu>) baseDao.findByCriteria(quickMenu);
        if(null == quickMenuList || quickMenuList.size() <= 0)return;
        for(Iterator<QuickMenu> it = quickMenuList.iterator();it.hasNext();){
            Integer ruleId = it.next().getRule().getRuleId();
            Rule rule = baseDao.getObject(Rule.class,ruleId);
            if(null != rule){
                CacheManager.putCache("quickMenu"+rule.getRuleId(),rule);//缓存用户快捷菜单的权限
            }
        }
    }

    public boolean checkLogin(String userName, String passwd) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Admin.class);
        criteria.add(Restrictions.eq("userName", userName));
        criteria.add(Restrictions.eq("passWord", passwd));
        List<Admin> adminList = (List<Admin>)baseDao.findByCriteria(criteria);
        return adminList.size() > 0 ? true : false;
    }
    public List<Rule> getMenuList(String userName){
        List<Rule> ruleList = new ArrayList<Rule>();

        List<String> cacheMenuKeyList = CacheManager.getCacheListkey("menu");
        if(null == cacheMenuKeyList || cacheMenuKeyList.size() <= 0){
            Admin admin = (Admin) CacheManager.getCacheInfo("adminInfo");
            Integer roleId = admin.getRole().getRoleId();
            DetachedCriteria role_rule = DetachedCriteria.forClass(Role_Rule.class);
            role_rule.add(Restrictions.ge("role.roleId",roleId));
            List<Role_Rule> roleRuleList = (List<Role_Rule>)baseDao.findByCriteria(role_rule);
            if(roleRuleList.size() <=0 )return null;

            for(Iterator<Role_Rule> it = roleRuleList.iterator();it.hasNext();){
                Integer ruleId = it.next().getRule().getRuleId();
                Rule rule = baseDao.getObject(Rule.class,ruleId);
                if(null != rule){
                    ruleList.add(rule);
                }
            }
        }
        for(Iterator<String> it = cacheMenuKeyList.iterator();it.hasNext();){
            ruleList.add((Rule) CacheManager.getCacheInfo(it.next()));
        }
        return ruleList;

    }
}
