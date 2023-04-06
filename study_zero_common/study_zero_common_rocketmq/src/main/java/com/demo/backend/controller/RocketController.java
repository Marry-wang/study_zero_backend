package com.demo.backend.controller;

import com.demo.backend.service.RocketMqService;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RocketController
 * @Author 王孟伟
 * @Date 2021/12/8 22:13
 * @Version 1.0
 */
@RestController
@RequestMapping("/")
public class RocketController {

    @Autowired
    private RocketMqService rocketMqService;

    @PostMapping("/send")
    public void send(){
        for(int i =0;i<10;i++){
            SendResult sendResult = rocketMqService.openAccountMsg("第" + i + "次消息");
            System.out.println(sendResult);
        }

    }
}
