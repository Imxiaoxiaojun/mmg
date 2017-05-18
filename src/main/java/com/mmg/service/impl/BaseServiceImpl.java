package com.mmg.service.impl;

import com.mmg.dao.BaseDao;
import com.mmg.entity.BaseObject;
import com.mmg.service.BaseService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
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

	public <T extends BaseObject> List<T> findByCriteria(DetachedCriteria criteria) {
		return baseDao.findByCriteria(criteria);
	}

	public <T extends BaseObject> List<T> findByCriteria(DetachedCriteria criteria, int from, int maxnum) {
		return baseDao.findByCriteria(criteria, from, maxnum);
	}

	public <T extends BaseObject> List<T> findByHql(String hql, Object... values) {
		return baseDao.findByHql(hql, values);
	}
	public Integer getObjectCountByHql(String hql,Object... values){
		return baseDao.getObjectCountByHql(hql,values);
	}
}
