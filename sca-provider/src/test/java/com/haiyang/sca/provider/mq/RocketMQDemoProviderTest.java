package com.haiyang.sca.provider.mq;


import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RocketMQDemoProviderTest {
    @Autowired
    private RocketMQDemoProvider rocketMQDemoProvider;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public void test() {
        rocketMQTemplate.convertAndSend("hywang-test","this is a message");
    }
    @Test
    public void  send(){
        send("test rocketmq");
    }

    public void send(String message) {
        try {

        DefaultMQProducer producer = new DefaultMQProducer("test_producer_group");
        producer.setNamesrvAddr("192.168.0.107:9876");
        producer.start();

        Message msg = new Message("test-topic", "test-tag", message.getBytes());
        producer.send(msg);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        }
    }
}
