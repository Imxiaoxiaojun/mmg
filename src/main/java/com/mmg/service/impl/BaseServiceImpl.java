package com.mmg.service.impl;

import com.mmg.dao.BaseDao;
import com.mmg.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yj on 2017/5/7.
 */
@Transactional
@Service("baseService")
public class BaseServiceImpl implements BaseService {
    @Autowired
    private BaseDao baseDao;

    public int countByHql(String hql, Object... values) {
        return this.baseDao.countByHql(hql,values);
    }
}
