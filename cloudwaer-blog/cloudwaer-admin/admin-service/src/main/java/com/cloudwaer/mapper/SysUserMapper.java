package com.cloudwaer.mapper;

import com.cloudwaer.domain.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Command;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * mapper接口
     *
     * @param code 用户ID
     * @return
     */
    @Select("select * from sys_user where id=${code}")
    SysUser findUserByUserCode(@Param("code") Long code);
}