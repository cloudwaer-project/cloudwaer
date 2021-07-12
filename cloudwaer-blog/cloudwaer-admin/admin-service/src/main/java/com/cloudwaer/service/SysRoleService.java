package com.cloudwaer.service;

import com.cloudwaer.domain.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @description: TODO
 * @author wenchang
 * @date 2021/7/12 16:47
 * @version 1.0
 */
public interface SysRoleService extends IService<SysRole>{
    /**
     * @description: 判断是否是后台管理员
     * @param: userId
     * @return: boolean
     * @date: 2021/7/12 16:53
     */
    boolean isSuperAdmin(Long userId);
}
