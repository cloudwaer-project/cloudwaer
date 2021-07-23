package com.cloudwaer.service;

import com.cloudwaer.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SysUserService extends IService<SysUser> {
    SysUser findUserByUserCode(Long Code);
}
