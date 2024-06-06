package com.demo.domain.login.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.annotation.SysLog;
import com.demo.api.ZeroResult;
import com.demo.domain.login.entry.dto.LoginDto;
import com.demo.domain.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/system")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/login")
    public ZeroResult<String> login(@RequestBody LoginDto dto) {
        return ZeroResult.success(loginService.getLoginToken(dto));
    }

    @SysLog
    @GetMapping(value = "/loginMessage")
    public ZeroResult<Object> loginMessage() {
        return ZeroResult.success(loginService.getLoginMessage());
    }

    @GetMapping(value = "/loginOut")
    public ZeroResult<Object> loginOut() {
        return ZeroResult.success(loginService.loginOut());
    }
}
