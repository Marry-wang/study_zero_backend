package com.demo.backend.config;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @ClassName RocketMsgListener
 * @Author 王孟伟
 * @Date 2021/12/8 9:51
 * @Version 1.0
 */
@Component
public class RocketMsgListener implements MessageListenerConcurrently {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(RocketMsgListener.class);
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        if(CollectionUtils.isEmpty(list)){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt =list.get(0);
        LOGGER.info("接收到的消息为:"+new String(messageExt.getBody()));
        int reconsumeTimes = messageExt.getReconsumeTimes();
        if(reconsumeTimes==3){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        String tags = messageExt.getTags();
        LOGGER.info("接收到的tags为:"+tags);

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
