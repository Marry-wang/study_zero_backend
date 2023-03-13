package com.demo.controller.fein;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author wangmengwei
 * @date 2022年06月08日 17:12
 */
@RestController
@RequestMapping(value = "/system")
public class ManageSystemController  {

    @PostMapping(value = "/send")
    public HashMap<String, String> send() {
        HashMap<String, String> map = new HashMap<>();
        map.put("system", "1");
        return map;
    }

}
