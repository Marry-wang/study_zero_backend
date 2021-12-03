package com.demo.backend.controller;

import com.demo.backend.dto.Result;
import com.demo.backend.dto.ResultFactory;
import com.demo.backend.model.BasePerson;
import com.demo.jwt.jwtconfig.JwtConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    /**
     * swagger  GET传参 发生错误以及解决办法
     * Required String parameter '******' is not present
     * 1 ApiParam 与 RequestParam  name属性不要冲突
     * 2 RequestParam  中 required=false
     */

    @Autowired
    private JwtConfig jwtConfig;

    @PostMapping("/loginTo")
    @ApiOperation(value = "登录", notes = "登录信息")
//    public Result login (@ApiParam(value="用户姓名",type = "query")@RequestParam(name = "userName",required = false) String userName,
//                         @ApiParam(value="用户密码",type = "query")@RequestParam(name = "passWord",required = false) String passWord) {
    public Result loginTo (@RequestBody BasePerson basePerson) {

        String userId = 5+"";
        String token = jwtConfig.createToken(userId);

        return ResultFactory.buildSuccess(token);
    }

    @RequestMapping("/getUserInfo")
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    public Result getUserInfo(HttpServletRequest request){
        String userNameFromToken = jwtConfig.getUsernameFromToken(request.getHeader("accessToken"));
        return ResultFactory.buildSuccess(userNameFromToken);
    }
}
