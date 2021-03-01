package com.haiyang.sca.provider.mq;


import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

@Service
public class RocketMQDemoProvider {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void sendMsg(){
        String msg = "this is a message by rocketmq provider";
        Message<String> message = new GenericMessage<>(msg);
        rocketMQTemplate.send(message);
    }
}
