package com.cloudwaer.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloudwaer.common.dto.ResponseCode;
import com.cloudwaer.common.dto.ResponseDto;
import com.cloudwaer.common.exception.ParamsException;
import com.cloudwaer.common.utils.ResponseUtils;
import com.cloudwaer.domain.SysPrivilege;
import com.cloudwaer.service.SysPrivilegeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * @ClassName SysPrivilegeController
 * @Description 权限操作   查询  修改   新增   删除
 * @Author jiushiboy
 * @Date 2021/7/8 11:27
 * @Version 1.0
 **/
@RestController
@RequestMapping("/privilege")
@Api(tags = "权限管理")
public class SysPrivilegeController {

    private Logger logger = LoggerFactory.getLogger(SysPrivilegeController.class);

    @Resource
    private SysPrivilegeService sysPrivilegeService;

    /**
     * 查询权限
     *
     * @return
     */
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页显示条数")
    })
    @PreAuthorize("hasAuthority('sys_privilege_query')")
    public ResponseDto queryPrivilege(@ApiIgnore Page<SysPrivilege> page) {
        try {
            logger.info("权限查询接口入参:{}", JSONObject.toJSONString(page));
            page.addOrder(OrderItem.desc("last_update_time"));
            page = sysPrivilegeService.page(page);
            logger.info("权限查询接口返参:{}", JSONObject.toJSONString(page));
            return ResponseUtils.buildVoByResponseCode(ResponseCode.SUCCESS);
        } catch (Exception e) {
            logger.info("权限查询接口异常:{}", JSONObject.toJSONString(e));
            return ResponseUtils.buildVoByResponseCode(ResponseCode.ERROR);
        }
    }

}
