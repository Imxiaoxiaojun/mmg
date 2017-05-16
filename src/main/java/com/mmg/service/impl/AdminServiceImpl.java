package com.mmg.service.impl;

import com.mmg.entity.admin.*;
import com.mmg.service.AdminService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
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
	public List<Rule> getAllRuleList() {
		return super.getAllObjects(Rule.class);
	}

	@Cacheable(value = "adminCache")
	public List<QuickMenu> getQuickMenuList(Integer admin_Id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(QuickMenu.class);
		criteria.add(Restrictions.ge("admin.id", admin_Id));
		criteria.setProjection(Projections.groupProperty("rule"));
		return super.findByCriteria(criteria);
	}

}
