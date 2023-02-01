package com.demo.controller.fein;

import com.demo.service.RemoteSystemService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author wangmengwei
 * @date 2022年06月08日 17:12
 */
@RestController
public class ManageSystemController implements RemoteSystemService {

    @Override
    public HashMap<String, String> send() {
        HashMap<String, String> map = new HashMap<>();
        map.put("system", "1");
        return map;
    }

}
