package com.demo.service;

import com.demo.factory.RemoteSystemFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;

/**
 * @author wangmengwei
 * @date 2022年06月09日 10:50
 */
@FeignClient( fallbackFactory = RemoteSystemFallBackFactory.class,url = "localhost:7002",name = "remoteSystemService")
public interface RemoteSystemService {

    /**
     * send
     * @return
     */
    @PostMapping("/system/send")
    HashMap<String,String> send();

}
