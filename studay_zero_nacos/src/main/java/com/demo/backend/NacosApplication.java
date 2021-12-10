package com.demo.backend;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName NacosApplication
 * @Author 王孟伟
 * @Date 2021/12/10 09:28
 * @Version 1.0
 */
@SpringBootApplication
@NacosPropertySource(dataId = "zero",autoRefreshed = true)
public class NacosApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosApplication.class, args);
    }
}
