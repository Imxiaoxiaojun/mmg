package com.mmg.dao;


import com.mmg.entity.BaseObject;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
@Repository("baseDao")
public class BaseDaoImpl implements BaseDao{
	
	@Autowired@Qualifier("hibernateTemplate")
	protected HibernateTemplate hibernateTemplate;
	
	public <T extends BaseObject> List<T> getAllObjects(Class<T> clazz){
		return hibernateTemplate.loadAll(clazz);
	}

	public <T extends BaseObject> T getObject(Class<T> clazz, Serializable id) {
		if(id==null||clazz==null) return null;
		T o = hibernateTemplate.get(clazz, id);
		return o;
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

	public <T extends BaseObject> int getObjectCount(Class<T> clazz) {
		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		criteria.setProjection(Projections.rowCount());
		List<?> result = hibernateTemplate.findByCriteria(criteria);
		if(result.isEmpty()) return 0;
		return Integer.parseInt("" + result.get(0));
	}

	public List<?> findByHql(String hql, Object... values) {
		return hibernateTemplate.find(hql, values);
	}

	public List<?> findByCriteria(DetachedCriteria criteria) {
		return hibernateTemplate.findByCriteria(criteria);
	}

	public List<?> findByCriteria(DetachedCriteria criteria, int from, int maxnum) {
		return hibernateTemplate.findByCriteria(criteria, from, maxnum);
	}

	
}
