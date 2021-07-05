package com.cloudwaer.service.impl;

import com.cloudwaer.domain.SysRole;
import com.cloudwaer.mapper.SysRoleMapper;
import com.cloudwaer.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public boolean isSuperAdmin(Long userId) {
        // 当用户角色code为 ROLE_ADMIN 则管理员拥有超级管理员权限
        String roleCode = sysRoleMapper.getUserRoleCode(userId);
        if (StringUtils.isNotEmpty(roleCode) && "ROLE_ADMIN".equals(roleCode)) {
            return true;
        }
        return false;
    }
}
