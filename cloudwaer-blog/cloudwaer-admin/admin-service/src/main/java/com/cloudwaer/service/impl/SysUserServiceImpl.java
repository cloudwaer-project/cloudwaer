package com.cloudwaer.service.impl;

import com.cloudwaer.common.dto.ResponseCode;
import com.cloudwaer.common.utils.ResponseUtils;
import com.cloudwaer.domain.SysUser;
import com.cloudwaer.mapper.SysUserMapper;
import com.cloudwaer.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.http.StringSplitUtils;
import org.springframework.stereotype.Service;


/**
 * service实现类
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {


    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * @param Code 用户ID  一般来说登录成功一定会有这个用户的个人信息所以不必要做多余的操作
     * @return
     */
    @Override
    public SysUser findUserByUserCode(Long Code) {
        SysUser userByUserCode = sysUserMapper.findUserByUserCode(Code);
        if (userByUserCode == null) {
            return null;
        }
        return userByUserCode;
    }
}
