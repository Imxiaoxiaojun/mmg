package com.mmg.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.mmg.dao.Dao;
import com.mmg.model.BaseObject;

public class BaseDao implements Dao{
	@Autowired@Qualifier("hibernateTemplate")
	protected HibernateTemplate hibernateTemplate;

	public <T extends BaseObject> List<T> getAllObjects(Class<T> clazz) {
		if(clazz==null) 
			return null;
		else{
			List<T> result = hibernateTemplate.loadAll(clazz);
			return result;
		}
	}

	public <T extends BaseObject> T saveObject(T entity) {
		if(entity!=null) {
			hibernateTemplate.saveOrUpdate(entity);
		}
		return entity;
	}

	public <T extends BaseObject> T removeObject(T entity) {
		if(entity != null) {
			hibernateTemplate.delete(entity);
		}
		return entity;
	}

	public <T extends BaseObject> T updateObject(T entity) {
		hibernateTemplate.update(entity);
		return entity;
	}

	public <T extends BaseObject> T addObject(T entity) {
		hibernateTemplate.save(entity);
		return entity;
	}

	//TODO
	public <T extends BaseObject> int getObjectCount(Class<T> clazz) {
		DetachedCriteria query = DetachedCriteria.forClass(clazz);
		query.setProjection(Projections.rowCount());
		List<?> result = hibernateTemplate.findByCriteria(query);
		if(result.isEmpty()) return 0;
		return Integer.parseInt("" + result.get(0));
	}

	public <T extends BaseObject> T removeObjectById(Class<T> clazz, Serializable id) {
		T entity = getObject(clazz, id);
		if(entity == null) {
			return null;
		}
		removeObject(entity);
		return entity;
	}
	
	
	
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public <T extends BaseObject> T getObject(Class<T> clazz, Serializable id) {
		if(id==null||clazz==null) return null;
		return hibernateTemplate.get(clazz, id);
	}
}
