package com.cloudwaer.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloudwaer.common.dto.ResponseCode;
import com.cloudwaer.common.dto.ResponseDto;
import com.cloudwaer.common.exception.ParamsException;
import com.cloudwaer.common.utils.DateTimeUtil;
import com.cloudwaer.common.utils.ResponseUtils;
import com.cloudwaer.domain.SysPrivilege;
import com.cloudwaer.service.SysPrivilegeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
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
            return ResponseUtils.buildVoByResponseCode(ResponseCode.SUCCESS, page);
        } catch (Exception e) {
            logger.info("权限查询接口异常:{}", JSONObject.toJSONString(e));
            return ResponseUtils.buildVoByResponseCode(ResponseCode.ERROR);
        }
    }

    /*** 
     * @description: 新增权限
     * @param: sysPrivilege
     * @return: com.cloudwaer.common.dto.ResponseDto
     * @date: 2021/7/13 16:02
     */
    @PostMapping(value = "savePrivilege")
    @PreAuthorize("hasAuthority('sys_privilege_create')")
    public ResponseDto savePrivilege(@RequestBody SysPrivilege sysPrivilege) {
        try {
            logger.info("权限保存接口入参:{}", JSONObject.toJSONString(sysPrivilege));
            savePrivilegeParamDispose(sysPrivilege);
            sysPrivilegeService.save(sysPrivilege);
            return ResponseUtils.buildVoByResponseCode(ResponseCode.SUCCESS);
        } catch (ParamsException pe) {
            logger.info("权限保存接口参数异常:{}", pe.getMsg());
            return ResponseUtils.buildVoByResponseCode(ResponseCode.PARAMS_ERROR, pe.getMsg());
        } catch (Exception e) {
            logger.info("权限保存接口异常:{}", e.getMessage());
            return ResponseUtils.buildVoByResponseCode(ResponseCode.ERROR, e.getMessage());
        }
    }
    
    /*** 
     * @description: 新增参数处理
     * @param: sysPrivilege 
     * @return: com.cloudwaer.domain.SysPrivilege 
     * @date: 2021/7/13 16:05
     */ 
    private void savePrivilegeParamDispose(SysPrivilege sysPrivilege) {
        // 1. 获取当前操作用户
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        sysPrivilege.setCreateBy(Long.valueOf(userId));
        // 2. 获取创建时间
        sysPrivilege.setCreated(DateTimeUtil.getCurrDate());
    }

    /**
     * @description: 修改权限
     * @param: sysPrivilege
     * @return: com.cloudwaer.common.dto.ResponseDto
     * @date: 2021/7/13 16:02
     */
    @PostMapping(value = "updatePrivilege")
    @PreAuthorize("hasAuthority('sys_privilege_update')")
    public ResponseDto updatePrivilege(@RequestBody SysPrivilege sysPrivilege) {
        try {
            logger.info("权限修改接口入参:{}", JSONObject.toJSONString(sysPrivilege));
            sysPrivilegeService.update(sysPrivilege, new QueryWrapper<SysPrivilege>().eq("id", sysPrivilege.getId()));
            return ResponseUtils.buildVoByResponseCode(ResponseCode.SUCCESS);
        } catch (ParamsException pe) {
            logger.info("权限修改接口参数异常:{}", pe.getMsg());
            return ResponseUtils.buildVoByResponseCode(ResponseCode.PARAMS_ERROR, pe.getMsg());
        } catch (Exception e) {
            logger.info("权限修改接口异常:{}", e.getMessage());
            return ResponseUtils.buildVoByResponseCode(ResponseCode.ERROR, e.getMessage());
        }
    }

}
