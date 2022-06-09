package com.demo.factory;

import com.demo.service.RemoteSystemService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author wangmengwei
 * @date 2022年06月09日 10:50
 */
@Component
public class RemoteSystemFallBackFactory implements FallbackFactory<RemoteSystemService> {

    @Override
    public RemoteSystemService create(Throwable cause) {
        return new RemoteSystemService() {
            @Override
            public HashMap<String, String> send() {
                HashMap<String , String > hashMap = new HashMap<>(6);
                hashMap.put("error","error");
                return hashMap;
            }

        };
    }
}
