package com.example.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.order.entity.OrderData;
import com.example.order.mapper.OrderMapper;
import com.example.order.service.OrderService;
import com.example.order.MQ.Producer; // 引入新的 Producer 类
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderData> implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private Producer producer; // 使用新的 Producer 类

    @Override
    @Transactional
    public Boolean processOrder(List<OrderData> orderData) {
        boolean saveResult = this.saveBatch(orderData);
            String destination = "delayed-order-topic";
            int delayLevel = 3; // 设置延迟级别为 1 分钟
        producer.sendDelayedMessage("Hello, delayed RocketMQ!");
        return saveResult;
    }
        @Override
        public List<OrderData> listAll () {
            return this.list();
        }

        @Override
        public boolean deleteOrderByIdNumber(String idNumber){
            logger.debug("Deleting order with ID number: {}", idNumber);

            QueryWrapper<OrderData> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id_number", idNumber);

            return this.remove(queryWrapper);
        }



}