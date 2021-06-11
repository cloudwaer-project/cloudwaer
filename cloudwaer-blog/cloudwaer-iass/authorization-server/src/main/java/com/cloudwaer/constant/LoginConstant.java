package com.cloudwaer.constant;

import java.util.List;

/**
 * @ClassName LoginConstant
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/10 14:53
 * @Version 1.0
 **/
public interface LoginConstant {
    /**
     * 登录类型
     */
    interface LoginTyoe {
        /**
         * 管理员
         */
        String ADMIN_TYPE = "admin_type";
        /**
         * 会员
         */
        String MEMBER_TYPE = "member_type";
        /**
         * refresh token
         */
        String REFRESH_TOKEN = "refresh_token";
    }

    /**
     * 用户角色
     */
    interface ClientRole {
        /**
         * 管理员角色
         */
        String ADMIN_ROLE_CODE = "ROLE_ADMIN";
    }

    /**
     * 登录相关SQL
     */
    interface LoginInfoSQL {
        /**
         * 查询指定管理员的 username
         */
        String QUERY_ADMIN_WHERE_USERNAME_BY_ID = "SELECT username  FROM sys_user  WHERE id = ?";
        /**
         * 查询指定会员的 username
         */
        String QUERY_MEMBER_WHERE_USERNAME_BY_ID = "SELECT username  FROM user  WHERE id = ?";

        /**
         * 管理员登录
         */
        String QUERY_ADMIN_SQL = "SELECT id,username,password,status FROM sys_user WHERE username=?";
        /**
         * 会员登录
         */
        public static final String QUERY_MEMBER_SQL = "SELECT `id`,username,`password`, `status` FROM `user` WHERE mobile = ? or email = ?";
        /**
         * 查询管理员全部权限
         */
        String QUERY_ADMIN_ROLE = "SELECT code FROM sys_role LEFT JOIN sys_user_role ON sys_role.id = sys_user_role.role_id WHERE sys_user_role.user_id= ?";
        /**
         * 查询管理员全部权限
         */
        String QUERY_ALL_PERMISSIONS = "SELECT name FROM sys_privilege";
        /**
         * 查询普通会员全部权限
         */
        String QUERY_PERMISSIONS = "SELECT NAME FROM sys_privilege LEFT JOIN sys_role_privilege ON sys_privilege.id = sys_role_privilege.privilege_id LEFT JOIN sys_user_role ON sys_role_privilege.role_id = sys_user_role.role_id  WHERE sys_user_role.user_id =?";
    }
}
