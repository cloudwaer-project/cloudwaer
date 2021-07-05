package com.cloudwaer.service;

import com.cloudwaer.domain.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SysMenuService extends IService<SysMenu>{


    List<SysMenu> getMenuByUserId(Long userId);
}
