package com.example.order.MQ;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RocketMQMessageListener(
        topic = "test-topic",
        consumerGroup = "my-consumer-group"
)
public class Consumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.out.println("Received at: " + new Date() + " | Message: " + message);
    }
}