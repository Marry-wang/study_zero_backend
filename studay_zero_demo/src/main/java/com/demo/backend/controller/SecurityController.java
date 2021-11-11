package com.demo.backend.controller;

import com.demo.backend.dto.Result;
import com.demo.backend.dto.ResultFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName SecurityController
 * @Author 王孟伟
 * @Date 2021/11/10 15:26
 * @Version 1.0
 */
@RestController
@RequestMapping("/security")
@Api(value = "security验证", tags = {"security验证"})
public class SecurityController {

    @RequestMapping("/hello")
    @ApiOperation(value = "helloworld", notes = "helloworld")
    public Result getUserInfo(HttpServletRequest request){

        return ResultFactory.buildSuccess("Hello Security");
    }

    @RequestMapping("/info")
    public String productInfo(){
        String currentUser = "";
        Object principl = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principl instanceof UserDetails) {
            currentUser = ((UserDetails)principl).getUsername();
        }else {
            currentUser = principl.toString();
        }
        return " some product info,currentUser is: "+currentUser;
    }
}
