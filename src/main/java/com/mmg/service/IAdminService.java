package com.mmg.service;

/**
 * Created by yj on 2017/5/6.
 */
public interface IAdminService extends BaseService {
    boolean checkLogin(String userName, String passWord);
}
