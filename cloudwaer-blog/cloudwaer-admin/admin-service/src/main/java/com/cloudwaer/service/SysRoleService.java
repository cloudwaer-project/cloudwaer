package com.cloudwaer.service;

import com.cloudwaer.domain.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SysRoleService extends IService<SysRole>{


    boolean isSuperAdmin(Long userId);
}
