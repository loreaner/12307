package com.example.order;

import com.example.order.MQ.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderApplicationTests {
    @Autowired
    Producer producer;

    @Test
    void contextLoads() {
        producer.sendDelayedMessage("Hello, RocketMQ!");
    }

}
