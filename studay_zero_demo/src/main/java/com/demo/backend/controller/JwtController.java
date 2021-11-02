package com.demo.backend.controller;

import com.demo.backend.dto.Result;
import com.demo.backend.dto.ResultFactory;
import com.demo.config.JwtConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName JwtController
 * @Author 王孟伟
 * @Date 2021/11/2 9:47
 * @Version 1.0
 */
@RestController
@RequestMapping("/jwt")
@Api(value = "jwt验证", tags = {"jwt验证"})
public class JwtController {

    @Autowired
    private JwtConfig jwtConfig;

    @RequestMapping("/login")
    @ApiOperation(value = "登录", notes = "登录信息")
    public Result login (@ApiParam(name="用户姓名",value="用户姓名")@RequestParam(name = "userName") String userName,
                         @ApiParam(name="用户密码",value="用户密码")@RequestParam(name = "passWord") String passWord) throws JSONException {

        String userId = 5+"";
        String token = jwtConfig.createToken(userId);

        return ResultFactory.buildSuccess(token);
    }

    @RequestMapping("/getUserInfo")
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    public Result getUserInfo(HttpServletRequest request){
        String userNameFromToken = jwtConfig.getUsernameFromToken(request.getHeader("token"));
        return ResultFactory.buildSuccess(userNameFromToken);
    }
}
