package com.cloudwaer.service;

import com.cloudwaer.domain.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
/**
 * @description: TODO
 * @author wenchang
 * @date 2021/7/12 16:53
 * @version 1.0
 */
public interface SysMenuService extends IService<SysMenu>{

    /**
     * 按照用户id查询菜单
     * @param userId
     * @return
     */
    List<SysMenu> getMenuByUserId(Long userId);
}
