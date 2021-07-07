package com.cloudwaer.service.impl;

import com.cloudwaer.constant.LoginConstant;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName UserServiceDetailsServiceImpl
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/10 13:56
 * @Version 1.0
 **/
@Component
public class UserServiceDetailsServiceImpl implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UserServiceDetailsServiceImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 校验登录用户是否正确,如果正确获取其全部的权限
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取到请求域
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 得到 login_type 请求参数,用来判断是管理员登录还是会员登录
        String loginType = requestAttributes.getRequest().getParameter("login_type");
        if (StringUtils.isEmpty(loginType)) {
            throw new AuthenticationServiceException("登录类型不能为空,请传入login_type");
        }

        UserDetails userDetails = null;
        // 这里为什么要用try catch  , 因为jdbc查询如果返回的是null 会报出异常 IncorrectResultSizeDataAccessException
        try {

            // 判断是否是 refresh token的登录方式
             String grantType = requestAttributes.getRequest().getParameter("grant_type");
           if (LoginConstant.LoginTyoe.REFRESH_TOKEN.equals(grantType)) {
                // 为什么要多此一举的改用户的名称,因为 refresh中需要存入的用户名是真正的用户名,而我在管理员和会员登录中取的列是ID列来代替用户名
                username = adjustUsername(username, loginType);
            }
            switch (loginType) {
                case LoginConstant.LoginTyoe.ADMIN_TYPE:
                    userDetails = loadAdminByUsername(username);
                    break;
                case LoginConstant.LoginTyoe.MEMBER_TYPE:
                    userDetails = loadMemberUserByUsername(username);
                    break;
                default:
                    throw new AuthenticationServiceException("尊敬的" + username + "用户,展示不支持" + loginType + "此种登录方式!");
            }
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new UsernameNotFoundException("用户名" + username + "不存在");
        }
        return userDetails;
    }

    /**
     * 会员登录
     *
     * @param username
     * @return
     */
    private UserDetails loadMemberUserByUsername(String username) {
        return jdbcTemplate.queryForObject(LoginConstant.LoginInfoSQL.QUERY_MEMBER_SQL, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                if (rs.wasNull()) {
                    throw new UsernameNotFoundException("用户名" + username + "不存在");
                }
                long id = rs.getLong("id");//会员名称
                String password = rs.getString("password");//会员的密码
                int status = rs.getInt("status"); //会员状态
                return new User(
                        String.valueOf(id),
                        password,
                        status == 1,
                        true,
                        true,
                        true,
                        Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
                );
            }
        },username,username);
    }

    /**
     * 管理员登录
     *
     * @param username
     * @return
     */
    private UserDetails loadAdminByUsername(String username) {
        return jdbcTemplate.queryForObject(LoginConstant.LoginInfoSQL.QUERY_ADMIN_SQL, new RowMapper<UserDetails>() {
            @Override
            public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
                if (rs.wasNull()) {
                    throw new UsernameNotFoundException("用户名" + username + "不存在!");
                }
                long id = rs.getLong("id"); //用户编号
                String password = rs.getString("password");//管理员密码
                int status = rs.getInt("status"); //停用状态 1 代表正常 0代表停用
                return new User(
                        String.valueOf(id),
                        password,
                        status == 1,
                        true,
                        true,
                        true,
                        getSyUserPermissions(id)
                );
            }
        }, username);
    }

    /**
     * 查询出管理员的全部权限
     *
     * @param id
     * @return
     */
    private Collection<? extends GrantedAuthority> getSyUserPermissions(long id) {
        String code = jdbcTemplate.queryForObject(LoginConstant.LoginInfoSQL.QUERY_ADMIN_ROLE, String.class, id);
        // 权限名称
        List<String> permissions = null;
        if (LoginConstant.ClientRole.ADMIN_ROLE_CODE.equals(code)) {
            permissions = jdbcTemplate.queryForList(LoginConstant.LoginInfoSQL.QUERY_ALL_PERMISSIONS, String.class);
        } else {
            permissions = jdbcTemplate.queryForList(LoginConstant.LoginInfoSQL.QUERY_PERMISSIONS, String.class, id);
        }
        if (null == permissions || permissions.isEmpty()) {
            return Collections.emptySet();
        }
        return permissions.stream().distinct() //去重
                .map(perm -> new SimpleGrantedAuthority(perm))
                .collect(Collectors.toSet());
    }

    /**
     * 当使用refresh token 时 矫正用户名称,不然username默认是存的ID
     *
     * @param username
     * @param loginType
     * @return
     */
    private String adjustUsername(String username, String loginType) {
        if (LoginConstant.LoginTyoe.ADMIN_TYPE.equals(loginType)) {
            return jdbcTemplate.queryForObject(LoginConstant.LoginInfoSQL.QUERY_ADMIN_WHERE_USERNAME_BY_ID, String.class, username);
        }
        if (LoginConstant.LoginTyoe.MEMBER_TYPE.equals(loginType)) {
            return jdbcTemplate.queryForObject(LoginConstant.LoginInfoSQL.QUERY_MEMBER_WHERE_USERNAME_BY_ID, String.class, username);
        }
        // 如果都没查到默认返回传入的username
        return username;
    }
}
