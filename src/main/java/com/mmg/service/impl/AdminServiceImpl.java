package com.mmg.service.impl;

import com.mmg.dao.BaseDaoImpl;
import com.mmg.service.IAdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by yj on 2017/5/6.
 */
@Transactional
@Service("adminService")
public class AdminServiceImpl extends BaseServiceImpl implements IAdminService {
    @Resource
    private BaseDaoImpl baseDao;

    public boolean checkLogin(String userName, String passWord) {
        String hql = "FROM Admin WHERE USERNAME = ? AND PASSWORD = ?";
        if (baseDao.countByHql(hql, userName, passWord) > 0) {
            return true;
        }
        return false;
    }
}
