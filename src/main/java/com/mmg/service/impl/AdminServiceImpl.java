package com.mmg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mmg.dao.BaseDao;

/**
 * Created by yj on 2017/5/6.
 */
@Transactional
@Service("adminService")
public class AdminServiceImpl extends BaseServiceImpl{
    @Autowired@Qualifier("baseDao")
    private BaseDao baseDao;
    
}
