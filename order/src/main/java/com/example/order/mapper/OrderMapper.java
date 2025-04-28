package com.example.order.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.order.entity.OrderData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<OrderData> {
}
