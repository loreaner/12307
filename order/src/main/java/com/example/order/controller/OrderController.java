package com.example.order.controller;


import com.example.order.entity.OrderData;
import com.example.order.service.OrderService;
import com.example.order.util.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    public ResponseResult submitOrder(@RequestBody List<OrderData> orderData) {
        log.info("Received order data: {}", orderData);

        try {
            if(orderService.processOrder(orderData)==true)
                return ResponseResult.success("Order submitted successfully");
            return ResponseResult.error("false");
        } catch (Exception e) {
            return ResponseResult.error("失败" + e.getMessage());
        }
    }
    @GetMapping("/list")
    public ResponseResult<List<OrderData>> getAllOrders() {
        List<OrderData> orders = orderService.listAll();
        return ResponseResult.success(orders);
    }

}
