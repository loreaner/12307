package com.example.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.order.entity.OrderData;
import com.example.order.mapper.OrderMapper;
import com.example.order.service.OrderService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderData> implements OrderService {

    @Override
    public Boolean processOrder(List<OrderData> orderData) {
        // 使用 MyBatis Plus 的 saveBatch 方法批量保存订单数据
       return this.saveBatch(orderData);
    }

    @Override
    public List<OrderData> listAll() {
        // 修改为调用 super.list() 来获取数据库中的全部数据
        return this.list();
    }

    @Override
    public Boolean deleteByIdNumber(String idNumber) {
        return this.removeById(idNumber);
    }
}