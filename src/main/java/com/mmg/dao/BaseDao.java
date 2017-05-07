package com.mmg.dao;

import java.io.Serializable;

/**
 * Created by yj on 2017/5/7.
 */
public interface BaseDao<T, ID extends Serializable> {
    int countByHql(String hql, Object... values);
}
