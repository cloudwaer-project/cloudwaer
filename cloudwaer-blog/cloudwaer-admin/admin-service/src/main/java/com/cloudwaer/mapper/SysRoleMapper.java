package com.cloudwaer.mapper;

import com.cloudwaer.domain.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface SysRoleMapper extends BaseMapper<SysRole> {
    String getUserRoleCode(@Param("userId") Long userId);
}