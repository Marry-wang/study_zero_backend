package com.demo.domain.login.service.impl;

import com.demo.domain.login.entry.dto.LoginDto;
import com.demo.domain.login.service.LoginService;
import com.demo.jwt.jwtconfig.JwtConfig;
import com.demo.template.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public String getLoginToken(LoginDto dto) {
        String token = jwtConfig.createToken(dto.getUserName() + dto.getUserPass());
        CacheUtil.set(token,token);
        return token;
    }

    @Override
    public String getLoginMessage(String token) {
        String redisMessage = CacheUtil.get(token);
        return jwtConfig.getUsernameFromToken(redisMessage).toString();
    }
}
