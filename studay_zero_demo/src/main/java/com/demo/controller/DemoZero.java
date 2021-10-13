package com.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DemoZero
 * @Author 王孟伟
 * @Date 2021/10/9 14:39
 * @Version 1.0
 */
@RestController
public class DemoZero {
    @RequestMapping("/")
    public String hello(){
        return "Hello";
    }
}
