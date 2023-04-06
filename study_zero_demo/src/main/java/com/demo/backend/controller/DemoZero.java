package com.demo.backend.controller;

import com.demo.backend.dto.Result;
import com.demo.backend.dto.ResultFactory;
import com.demo.backend.model.BasePerson;
import com.demo.jwt.jwtconfig.JwtConfig;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DemoZero
 * @Author 王孟伟
 * @Date 2021/10/9 14:39
 * @Version 1.0
 */
@RestController
public class DemoZero {

    @Autowired
    private JwtConfig jwtConfig;

    @GetMapping("/")
    public String hello() {
        return "Hello";
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "登录信息")
//    public Result login (@ApiParam(value="用户姓名",type = "query")@RequestParam(name = "userName",required = false) String userName,
//                         @ApiParam(value="用户密码",type = "query")@RequestParam(name = "passWord",required = false) String passWord) {
    public Result login (@RequestBody BasePerson basePerson) {

        String userId = 5+"";
        String token = jwtConfig.createToken(userId);

        return ResultFactory.buildSuccess(token);
    }
}
