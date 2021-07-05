package com.cloudwaer.service;


import com.cloudwaer.model.LoginResult;

/**
 * @ClassName SysLoginService
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/7/5 15:38
 * @Version 1.0
 **/
public interface SysLoginService {
    /**
     * 登录接口
     *
     * @param username 用户名
     * @param password 用户密码
     * @return
     */
    LoginResult login(String username, String password);
}
