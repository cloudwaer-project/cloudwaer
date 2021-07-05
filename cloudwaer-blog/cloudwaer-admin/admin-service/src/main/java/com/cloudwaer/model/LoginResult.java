package com.cloudwaer.model;

import com.cloudwaer.domain.SysMenu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

/**
 * @ClassName LoginResult
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/7/5 15:12
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "登录反参")
public class LoginResult {
    /**
     *  来自 authorizetion-server
     */
    @ApiModelProperty(value = "登录成功返回token,来自authorizetion-server服务")
    private String token;

    /**
     * 用户的菜单数据
     */
    @ApiModelProperty(value = "用户菜单数据")
    public List<SysMenu> menus;

    /**
     * 用户权限数据
     */
    @ApiModelProperty(value = "用户权限")
    private List<SimpleGrantedAuthority> authorities;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<SysMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<SysMenu> menus) {
        this.menus = menus;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
