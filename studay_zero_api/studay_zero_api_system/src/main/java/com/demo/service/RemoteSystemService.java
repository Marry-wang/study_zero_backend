package com.demo.service;

import com.demo.factory.RemoteSystemFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;

/**
 * @author wangmengwei
 * @date 2022年06月09日 10:50
 */

/**
 * feign的两种调用方式差异
 * https://blog.51cto.com/Tlog4J/5148220
 * https://www.cnblogs.com/lifacheng/p/11183549.html
 */
@FeignClient( fallbackFactory = RemoteSystemFallBackFactory.class,url = "localhost:7002",name = "studay-zero-system")
public interface RemoteSystemService {

    /**
     * send
     * @return
     */
    @PostMapping("/system/send")
    HashMap<String,String> send();

}
