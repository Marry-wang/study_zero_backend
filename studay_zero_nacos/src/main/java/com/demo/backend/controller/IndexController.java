package com.demo.backend.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IndexController
 * @Author 王孟伟
 * @Date 2021/12/10 10:27
 * @Version 1.0
 */
@RestController
@NacosPropertySource(dataId = "zero", autoRefreshed = true)
public class IndexController {

    @NacosValue(value = "${name}", autoRefreshed = true)
    private String testProperties;

    @RequestMapping("/")
    public String index(){
        return "hi dalaoyang-->"+testProperties;
    }

}
