package com.mmg.service.impl;

import com.mmg.entity.admin.*;
import com.mmg.service.AdminService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by yj on 2017/5/6.
 */
@Transactional
@Service("adminService")
public class AdminServiceImpl extends BaseServiceImpl implements AdminService{
    
    @Cacheable(value = "adminCache")
    public Admin getAdminInfo(String userName) {
            DetachedCriteria criteria = DetachedCriteria.forClass(Admin.class);
            criteria.add(Restrictions.eq("adminId", userName));
            return (Admin)super.findByCriteria(criteria).get(0);
    }

    public boolean checkLogin(String userName, String passwd) {
        String hql = "from Admin where adminId = ? and passWord = ?";
        return super.getObjectCountByHql(hql,userName,passwd) >0 ? true : false;
    }

    @Cacheable(value = "adminCache")
    public List<User_Role> getUser2RoleList(Integer admin_Id){
        DetachedCriteria criteria = DetachedCriteria.forClass(User_Role.class);
        criteria.add(Restrictions.ge("admin.id",admin_Id));
        return super.findByCriteria(criteria);
    }

    @Cacheable(value = "adminCache")
    public List<Role_Rule> getRole2RuleList(Collection<Integer> roleIdList){
        Set<Role_Rule> rrset = new HashSet<Role_Rule>();
        for(Integer id : roleIdList){
            DetachedCriteria criteria = DetachedCriteria.forClass(Role_Rule.class);
            criteria.add(Restrictions.ge("role.id",id));
            List<Role_Rule> list = super.findByCriteria(criteria);
            rrset.addAll(list);
        }
        return new ArrayList<Role_Rule>(rrset);
    }

    @Cacheable(value = "adminCache")
    public List<Rule> getAllRuleList(){
        return super.getAllObjects(Rule.class);
    }

    public List<QuickMenu> getQuickMenuList(Integer admin_Id) {
        return null;
    }
    

    
    public void loadUserToCache(String userName) {
/*
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
        }*/
    }

}
