package com.example.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.order.entity.OrderData;
import com.example.order.mapper.OrderMapper;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/order")
@RestController
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;
    @Autowired
    OrderMapper orderMapper;

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
    @PostMapping("/delete")
    public ResponseResult deleteOrderById(@RequestParam String idNumber) {
        QueryWrapper<OrderData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id_number", idNumber.toString());
        System.out.println(idNumber);
        if(orderMapper.delete(queryWrapper)>0)
        return ResponseResult.success("成功");
        return ResponseResult.error("失败");
    }

}
