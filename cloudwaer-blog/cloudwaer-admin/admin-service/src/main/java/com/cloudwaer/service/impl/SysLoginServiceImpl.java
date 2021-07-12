package com.cloudwaer.service.impl;

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

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @ClassName SysLoginServiceImpl
 * @Description TODO
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
        log.info("登录接口入参:{}");
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
        loginResult.setToken(jwtToken.getTokenType()+" "+token  );
        loginResult.setMenus(menus);
        loginResult.setAuthorities(
                authorities.
                        stream().
                        map(authority -> new SimpleGrantedAuthority(authority.toString())).
                        collect(Collectors.toList())
        );
        log.info("登录接口返参:{}", JSONObject.toJSONString(loginResult));
        // 将token存入到redis缓存
        redisTemplate.opsForValue().set(token,"",jwtToken.getExpiresIn(), TimeUnit.SECONDS);
        return loginResult;
    }
}
