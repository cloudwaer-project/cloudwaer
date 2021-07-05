package com.cloudwaer.service.impl;

import com.cloudwaer.domain.SysUser;
import com.cloudwaer.mapper.SysUserMapper;
import com.cloudwaer.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}
