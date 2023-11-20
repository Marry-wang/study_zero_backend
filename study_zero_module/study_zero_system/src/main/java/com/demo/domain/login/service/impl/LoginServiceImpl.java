package com.demo.domain.login.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.demo.config.JwtConfig;
import com.demo.domain.login.entry.dto.LoginDto;
import com.demo.domain.login.service.LoginService;
import com.demo.template.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public String getLoginToken(LoginDto dto) {
        String token = jwtConfig.createToken(JSONObject.toJSONString(dto));
        CacheUtil.set(token, token, TimeUnit.SECONDS);
        return token;
    }

    @Override
    public JSONObject getLoginMessage(String token) {
        String redisMessage = CacheUtil.get(token);
        String message = jwtConfig.getUsernameFromToken(redisMessage);
        return JSONObject.parseObject(message);
    }
}
