package com.cloudwaer.controller;

import com.cloudwaer.common.dto.ResponseCode;
import com.cloudwaer.common.dto.ResponseDto;
import com.cloudwaer.common.exception.ParamsException;
import com.cloudwaer.common.utils.ResponseUtils;
import com.cloudwaer.model.LoginResult;
import com.cloudwaer.service.SysLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
public class SysLoginController {
    private Logger logger = LoggerFactory.getLogger(SysLoginController.class);

    @Resource
    private SysLoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "后台管理员登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    public ResponseDto loginResult(
            @RequestParam(required = true) String username,
            @RequestParam(required = true) String password) {
        try {
            logger.info("后台管理登录接口入参:{}", username);
            LoginResult login = loginService.login(username, password);
            logger.info("后台管理登录接口返参:{}", login);
            return ResponseUtils.buildVoByResponseCode(ResponseCode.SUCCESS, login);
        } catch (ParamsException pe) {
            logger.info("后台管理登录接口参数异常:{}", pe);
            return ResponseUtils.buildVoByResponseCode(ResponseCode.PARAMS_ERROR, pe.getMsg());
        } catch (Exception e) {
            logger.info("后台管理登录接口异常:{}", e);
            return ResponseUtils.buildVoByResponseCode(ResponseCode.ERROR, e.getMessage());
        }
    }

}
