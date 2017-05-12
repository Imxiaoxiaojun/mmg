package com.mmg.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.mmg.entity.BaseObject;

public interface BaseService {
	<T extends BaseObject> List<T> getAllObjects(Class<T> clazz);
	<T extends BaseObject> T getObject(Class<T> clazz, Serializable id);
	<T extends BaseObject> T removeObject(T entity);
	<T extends BaseObject> T updateObject(T entity);
	<T extends BaseObject> T addObject(T entity);
	<T extends BaseObject> int getObjectCount(Class<T> clazz);
	List<?> findByCriteria(DetachedCriteria criteria);
	List<?> findByCriteria(DetachedCriteria criteria, int from, int maxnum);
	List<?> findByHql(String hql, Object... values);
}
