package com.demo.domain.login.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.config.JwtConfig;
import com.demo.context.SecurityContextHolder;
import com.demo.domain.login.entry.dto.LoginDto;
import com.demo.domain.login.service.LoginService;
import com.demo.domain.system.entry.po.SysUserPo;
import com.demo.domain.system.mapper.SysUserMapper;
import com.demo.enums.BaseResultEnum;
import com.demo.exception.BaseException;
import com.demo.template.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public String getLoginToken(LoginDto dto) {
        SysUserPo sysUserPo = sysUserMapper.selectOne(new QueryWrapper<SysUserPo>()
                .lambda()
                .eq(SysUserPo::getUserName, dto.getUserName()));
        if(ObjectUtil.isEmpty(sysUserPo)){
            throw new BaseException(BaseResultEnum.USERNOTEXIT);
        }

        String token = jwtConfig.createToken(JSONObject.toJSONString(dto));
        CacheUtil.set(token, token, TimeUnit.SECONDS);
        return token;
    }

    @Override
    public Object getLoginMessage(String token) {
        return SecurityContextHolder.getLocalMap();
    }
}
