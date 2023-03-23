package com.demo.backend.service.impl;

import com.demo.backend.service.RocketMqService;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName RocketMqServiceImpl
 * @Author 王孟伟
 * @Date 2021/12/8 10:21
 * @Version 1.0
 */
@Service
public class RocketMqServiceImpl implements RocketMqService {
    @Autowired
    private DefaultMQProducer defaultMQProducer;


    @Override
    public SendResult openAccountMsg(String msgInfo) {
        // 可以不使用Config中的Group
        defaultMQProducer.setProducerGroup("zero");
        SendResult sendResult = null;
        try {
            Message sendMsg = new Message("ZeroTopic",
                    "zero",
                    "open_account_key", msgInfo.getBytes());
            sendResult = defaultMQProducer.send(sendMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult ;
    }
    public void main(String[] msg){


    }
}
