package com.demo.backend.service.impl;

import com.demo.backend.service.IndexService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 * @ClassName IndexServiceImpl
 * @Author 王孟伟
 * @Date 2021/12/14 16:00
 * @Version 1.0
 */
@RefreshScope
@Service
public class IndexServiceImpl implements IndexService {

    @Value("${name:zero}")
    private String testProperties;

    @Override
    public String index() {
        return testProperties;
    }
}
