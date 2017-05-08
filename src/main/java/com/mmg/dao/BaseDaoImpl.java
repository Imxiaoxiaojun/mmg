package com.mmg.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Created by yj on 2017/5/7.
 */
@Repository
public class BaseDaoImpl<T ,ID extends Serializable> implements BaseDao<T,ID> {
    @Autowired
    private SessionFactory sessionFactory;
    protected Class<T> entityClass;

    private Session getSession(){
        return  sessionFactory.openSession();
    }

    public Class<T> getEntityClass() {
        if (entityClass == null) {
//            entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return entityClass;
    }

    public int countByHql(String hql, Object... values) {
        Query query = this.getSession().createQuery(hql);
        if(values != null){
            for(int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query.list().size();
    }
}
