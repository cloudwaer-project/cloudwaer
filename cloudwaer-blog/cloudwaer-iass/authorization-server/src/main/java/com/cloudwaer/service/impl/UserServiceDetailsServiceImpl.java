package com.cloudwaer.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @ClassName UserServiceDetailsServiceImpl
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/10 13:56
 * @Version 1.0
 **/
@Component
public class UserServiceDetailsServiceImpl implements UserDetailsService {

    /**
     * 校验登录用户是否正确,如果正确获取其全部的权限
     * @param  username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取到请求域
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 得到 login_type 请求参数,用来判断是管理员登录还是会员登录
        String login_type = requestAttributes.getRequest().getParameter("login_type");

        return null;
    }
}
