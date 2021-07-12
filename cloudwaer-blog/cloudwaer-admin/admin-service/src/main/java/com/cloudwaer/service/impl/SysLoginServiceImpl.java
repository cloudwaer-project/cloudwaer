package com.cloudwaer.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.cloudwaer.common.dto.ResponseCode;
import com.cloudwaer.common.exception.ParamsException;
import com.cloudwaer.common.utils.ParamUtils;
import com.cloudwaer.domain.SysMenu;
import com.cloudwaer.feign.JwtToken;
import com.cloudwaer.feign.OAuth2FeignClient;
import com.cloudwaer.model.LoginResult;
import com.cloudwaer.service.SysLoginService;
import com.cloudwaer.service.SysMenuService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.enums.ApiErrorCode;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @ClassName SysLoginServiceImpl
 * @Description 后台管理登录处理
 * @Author jiushiboy
 * @Date 2021/7/5 15:40
 * @Version 1.0
 **/
@Service
@Slf4j
public class SysLoginServiceImpl implements SysLoginService {

    @Resource
    private OAuth2FeignClient auth2FeignClient;

    @Resource
    private SysMenuService sysMenuService;

    @Value("${basic.token:Basic Y2xvdWR3YWVyLWFwaTpjbG91ZHdhZXItc2VjcmV0}")
    private String basicToken;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 登录实现
     *
     * @param username 用户名
     * @param password 用户密码
     * @return
     */
    @Override
    public LoginResult login(String username, String password) {
        // 0.参数校验
        if (StringUtils.isEmpty(username) && StringUtils.isEmpty(password)) {
            throw new ParamsException(ResponseCode.PARAMS_ERROR,"账号或密码为空","username","password");
        }
        log.info("登录接口入参:{}", username);
        // 1.获取token,远程调用服务获取token
        ResponseEntity<JwtToken> result = auth2FeignClient.getToken("password", username, password, "admin_type", basicToken);
        if (result.getStatusCode() != HttpStatus.OK) {
            throw new ApiException(ApiErrorCode.FAILED);
        }
        JwtToken jwtToken = result.getBody();
        String token = jwtToken.getAccessToken();
        log.info("token接口返回参数:{}", JSONObject.toJSONString(jwtToken));

        // 2.获取用户菜单数据
        Jwt jwt = JwtHelper.decode(token);
        String jwtJson = jwt.getClaims();
        JSONObject jsonObject = JSONObject.parseObject(jwtJson);
        Long userId = Long.valueOf(jsonObject.getString("user_name"));
        List<SysMenu> menus = sysMenuService.getMenuByUserId(userId);
        // 3.获取用户权限数据
        JSONArray authorities = jsonObject.getJSONArray("authorities");
        LoginResult loginResult = new LoginResult();
        loginResult.setToken(jwtToken.getTokenType() + " " + token);
        // 4.递归处理菜单数据
        loginResult.setMenus(disposeMenus(menus));
        // 5.设置权限
        loginResult.setAuthorities(
                authorities.
                        stream().
                        map(authority -> new SimpleGrantedAuthority(authority.toString())).
                        collect(Collectors.toList())
        );
        // 6.设置用户编号
        loginResult.setUserId(userId);
        log.info("登录接口返参:{}", JSONObject.toJSONString(loginResult));
        // 将token存入到redis缓存
        redisTemplate.opsForValue().set(token, "", jwtToken.getExpiresIn(), TimeUnit.SECONDS);
        return loginResult;
    }

    /**
     * 处理菜单数据
     * @param menus
     * @return
     */
    private List<SysMenu> disposeMenus(List<SysMenu> menus) {
        // 0. 传入的集合为空直接返回null
        if (CollectionUtils.isEmpty(menus)) {
            return null;
        }
        // 1. 定义最终返回数据
        List<SysMenu> result = Lists.newArrayList();
        // 2. 筛选出所有父节点数据, 也就是一级菜单,只要parentId为null的那就是一级菜单
        result = menus.stream().filter(menu -> null == menu.getParentId()).collect(Collectors.toList());
        // 3. 遍历所有的一级菜单为一级菜单添加子节点
        for (SysMenu sysMenu : result) {
            // 4. 查询子节点菜单集合,若出现子节点为父节点的情况会进行递归
            List<SysMenu> childMenus = queryChildMenus(sysMenu.getId(), menus);
            // 5. 设置子节点菜单数据
            sysMenu.setChildMenus(childMenus);
        }
        // 6. 返回菜单数据
        return result;
    }

    /**
     * 递归查询子菜单数据
     * @param id
     * @param menus
     * @return
     */
    private List<SysMenu> queryChildMenus(Long id, List<SysMenu> menus) {
        // 1. 定义子菜单数据返回结果
        List<SysMenu> childMenus = Lists.newArrayList();
        // 2. 筛选出当前传入节点(这里的节点根据id来判断)的子节点数据
        childMenus = menus.stream().filter(menu -> id.equals(menu.getParentId())).collect(Collectors.toList());
        // 3. 不为空证明传入的数据拥有子节点数据
        if (CollectionUtils.isNotEmpty(childMenus)) {
            // 4. 循环遍历进行递归调用
            for (SysMenu childMenu : childMenus) {
                List<SysMenu> sysMenus = queryChildMenus(childMenu.getId(), menus);
                childMenu.setChildMenus(sysMenus);
            }
        }
        // 5. 返回最终数据
        return childMenus;
    }
}
