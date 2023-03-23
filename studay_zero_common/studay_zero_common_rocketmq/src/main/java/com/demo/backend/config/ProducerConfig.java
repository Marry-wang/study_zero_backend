package com.demo.backend.config;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * @ClassName ProducerConfig
 * @Author 王孟伟
 * @Date 2021/12/8 9:38
 * @Version 1.0
 */
@Configuration
public class ProducerConfig {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ProducerConfig.class);
    @Value("${rocketmq.producer.groupName}")
    private String groupName;
    @Value("${rocketmq.producer.namesrvAddr}")
    private String namesrvAddr;
    @Value("${rocketmq.producer.maxMessageSize}")
    private Integer maxMessageSize ;
    @Value("${rocketmq.producer.sendMsgTimeout}")
    private Integer sendMsgTimeout;
    @Value("${rocketmq.producer.retryTimesWhenSendFailed}")
    private Integer retryTimesWhenSendFailed;


    @Bean
    public DefaultMQProducer getRocketMQProducer(){
        DefaultMQProducer producer;
        producer = new DefaultMQProducer(groupName);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setVipChannelEnabled(false);


        //如果需要同一个jvm中不同的producer往不同的mq集群发送消息，需要设置不同的instanceName
        if(maxMessageSize!=null){
            producer.setMaxMessageSize(maxMessageSize);
        }
        if(sendMsgTimeout!=null){
            producer.setSendMsgTimeout(sendMsgTimeout);
        }
        //如果发送消息失败，设置重试次数，默认为2次
        if(retryTimesWhenSendFailed!=null){
            producer.setRetryTimesWhenSendFailed(retryTimesWhenSendFailed);
        }
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return producer;
    }


}
