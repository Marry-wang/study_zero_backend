package com.demo.backend.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.demo.backend.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IndexController
 * @Author 王孟伟
 * @Date 2021/12/10 10:27
 * @Version 1.0
 */
@RestController
public class IndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping("/")
    public String index(){
        return indexService.index();
    }

}
