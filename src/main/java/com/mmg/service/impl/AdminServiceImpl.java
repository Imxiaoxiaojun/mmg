package com.mmg.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mmg.common.Page;
import com.mmg.entity.BaseObject;
import com.mmg.entity.admin.Admin;
import com.mmg.entity.admin.QuickMenu;
import com.mmg.entity.admin.Role;
import com.mmg.entity.admin.Role_Rule;
import com.mmg.entity.admin.Rule;
import com.mmg.entity.admin.User_Role;
import com.mmg.service.AdminService;

/**
 * Created by yj on 2017/5/6.
 */
@Transactional
@Service("adminService")
public class AdminServiceImpl extends BaseServiceImpl implements AdminService {

	@Cacheable(value = "adminCache")
	public Admin getAdminInfo(String userName) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Admin.class);
		criteria.add(Restrictions.eq("adminId", userName));
		return (Admin) super.findByCriteria(criteria).get(0);
	}

	public boolean checkLogin(String userName, String passwd) {
		String hql = "from Admin where adminId = ? and passWord = ?";
		return super.getObjectCountByHql(hql, userName, passwd) > 0 ? true : false;
	}

	@Cacheable(value = "adminCache")
	public List<Role> getRoleList(Integer admin_Id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User_Role.class);
		criteria.add(Restrictions.ge("admin.id", admin_Id));
		criteria.setProjection(Projections.groupProperty("role"));
		return super.findByCriteria(criteria);
	}
	
	public <T extends BaseObject> List<T> getPageList(Class<T> clazz,Page<T> page){
		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		List<T> ruleList = super.findByCriteria(criteria,page.getStartRow(),page.getPageSize());
		return ruleList;
	}
	
	@Cacheable(value = "adminCache")
	public List<Rule> getRuleList(Collection<Object> roleIdList) {
		Set<Rule> ruleList = new HashSet<Rule>();
		for (Object id : roleIdList) {
			DetachedCriteria criteria = DetachedCriteria.forClass(Role_Rule.class);
			criteria.add(Restrictions.ge("role.id", id));
			criteria.setProjection(Projections.groupProperty("rule"));
			List<Rule> list = super.findByCriteria(criteria);
			ruleList.addAll(list);
		}
		return new ArrayList<Rule>(ruleList);
	}

	@Cacheable(value = "adminCache")
	public	List<Rule> getAllRuleList(Class<Rule> clazz) {
		return super.getAllObjects(clazz);
	}
	@Cacheable(value = "adminCache")
	public  List<Role> getAllRoleList(Class<Role> clazz) {
		return super.getAllObjects(clazz);
	}
	@Cacheable(value = "adminCache")
	public  List<Admin> getAllAdminList(Class<Admin> clazz) {
		return super.getAllObjects(clazz);
	}

	@Cacheable(value = "adminCache")
	public List<QuickMenu> getQuickMenuList(Integer admin_Id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(QuickMenu.class);
		criteria.add(Restrictions.ge("admin.id", admin_Id));
		criteria.setProjection(Projections.groupProperty("rule"));
		return super.findByCriteria(criteria);
	}
	
	@CacheEvict(value = "adminCache",key = "#entity.class")
	public <T extends BaseObject> T addObject(T entity){
		return super.addObject(entity);
	}
	
	@CacheEvict(value = "adminCache",key = "#entity.class")
	public <T extends BaseObject> T removeObject(T entity){
		return super.removeObject(entity);
	}

	@CacheEvict(value = "adminCache",key = "#entity.class")
	public <T extends BaseObject> T updateObject(T entity){
		return super.updateObject(entity);
	}
}
