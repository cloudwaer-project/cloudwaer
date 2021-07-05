package com.cloudwaer.service.impl;

import com.cloudwaer.domain.SysMenu;
import com.cloudwaer.mapper.SysMenuMapper;
import com.cloudwaer.service.SysMenuService;
import com.cloudwaer.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> getMenuByUserId(Long userId) {
        // 1.超级管理拥有全部权限
        if (sysRoleService.isSuperAdmin(userId)){
            return list();
        }
        // 2.否则查询
        return sysMenuMapper.selectMenuByUserId(userId);
    }
}
