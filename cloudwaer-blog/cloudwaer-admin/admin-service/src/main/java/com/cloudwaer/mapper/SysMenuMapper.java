package com.cloudwaer.mapper;

import com.cloudwaer.domain.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<SysMenu> selectMenuByUserId(Long userId);
}