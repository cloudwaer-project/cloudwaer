package com.cloudwaer.controller;

import com.cloudwaer.model.LoginResult;
import com.cloudwaer.service.SysLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName SysLoginController
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/7/5 17:29
 * @Version 1.0
 **/
@RestController
@Api(value = "登录控制器")
public class SysLoginController {


    @Resource
    private SysLoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "后台管理员登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    public LoginResult loginResult(
            @RequestParam(required = true) String username,
            @RequestParam(required = true) String password) {
        return loginService.login(username, password);
    }

}
