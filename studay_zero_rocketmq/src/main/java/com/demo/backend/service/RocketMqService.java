package com.demo.backend.service;

import org.apache.rocketmq.client.producer.SendResult;

/**
 * @ClassName RocketMqService
 * @Author 王孟伟
 * @Date 2021/12/8 10:22
 * @Version 1.0
 */
public interface RocketMqService {
    public SendResult openAccountMsg(String msgInfo);
}
