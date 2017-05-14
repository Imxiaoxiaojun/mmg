package com.mmg.dao;

import com.mmg.entity.BaseObject;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.cache.annotation.Cacheable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yj on 2017/5/14.
 */
public class BaseCacheImpl implements BaseDao{
    @Cacheable(value = "")
    public <T extends BaseObject> List<T> getAllObjects(Class<T> clazz) {
        return null;
    }

    public <T extends BaseObject> T getObject(Class<T> clazz, Serializable id) {
        return null;
    }

    public <T extends BaseObject> T removeObject(T entity) {
        return null;
    }

    public <T extends BaseObject> T updateObject(T entity) {
        return null;
    }

    public <T extends BaseObject> T addObject(T entity) {
        return null;
    }

    public <T extends BaseObject> int getObjectCount(Class<T> clazz) {
        return 0;
    }

    public List<?> findByHql(String hql, Object... values) {
        return null;
    }

    public List<?> findByCriteria(DetachedCriteria criteria) {
        return null;
    }

    public List<?> findByCriteria(DetachedCriteria criteria, int from, int maxnum) {
        return null;
    }
}
