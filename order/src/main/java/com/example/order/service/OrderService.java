package com.example.order.service;

import com.example.order.entity.OrderData;

import java.util.List;

public interface OrderService {

    Boolean processOrder(List<OrderData> orderData);

    List<OrderData> listAll();

    public boolean deleteOrderByIdNumber(String idNumber);


}