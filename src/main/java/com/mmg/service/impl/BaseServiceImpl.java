package com.mmg.service.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mmg.dao.BaseDao;
import com.mmg.entity.BaseObject;
import com.mmg.service.BaseService;
@Transactional
@Service("baseDaoService")
public class BaseServiceImpl implements BaseService{
	@Autowired@Qualifier("baseDao")
	protected BaseDao baseDao;

	
	public <T extends BaseObject> List<T> getAllObjects(Class<T> clazz) {
		return baseDao.getAllObjects(clazz);
	}

	public <T extends BaseObject> T getObject(Class<T> clazz, Serializable id) {
		return baseDao.getObject(clazz, id);
	}

	public <T extends BaseObject> T removeObject(T entity) {
		return baseDao.removeObject(entity);
	}

	public <T extends BaseObject> T updateObject(T entity) {
		return baseDao.updateObject(entity);
	}

	public <T extends BaseObject> T addObject(T entity) {
		return baseDao.addObject(entity);
	}

	public <T extends BaseObject> int getObjectCount(Class<T> clazz) {
		return baseDao.getObjectCount(clazz);
	}

	public List<?> findByCriteria(DetachedCriteria criteria) {
		return baseDao.findByCriteria(criteria);
	}

	public List<?> findByCriteria(DetachedCriteria criteria, int from, int maxnum) {
		return baseDao.findByCriteria(criteria, from, maxnum);
	}

	public List<?> findByHql(String hql, Object... values) {
		return baseDao.findByHql(hql, values);
	}

}
