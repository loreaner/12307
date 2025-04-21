package com.example.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.user.entity.Passenger;
import com.example.user.mapper.PassengerMapper;
import com.example.user.service.IPassengerService;
import com.example.user.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl extends ServiceImpl<PassengerMapper, Passenger> implements IPassengerService {
    @Autowired
    private PassengerMapper passengerMapper;

    @Override
    public List<Passenger> searchPassengersByName(String name) {
        QueryWrapper<Passenger> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", name);
        return passengerMapper.selectList(queryWrapper);
    }

    @Override
    public int batchDeletePassengers(List<Long> ids) {
       return passengerMapper.deleteBatchIds(ids);
    }

    @Override
    public boolean addPassenger(Passenger passenger) {
        QueryWrapper<Passenger> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id_type", passenger.getIdType())
                .eq("id_number", passenger.getIdNumber());
        boolean exists = passengerMapper.exists(queryWrapper);
        if (exists) {
            throw new RuntimeException("证件信息已存在");
        }
        return passengerMapper.insert(passenger) > 0;
    }


    @Override
    public boolean updatePassenger(Passenger passenger) {
        // 检查传入的Passenger对象是否包含有效的id
        if (passenger.getId() == null) {
            throw new IllegalArgumentException("乘客ID不能为空");
        }
        // 调用MyBatis-Plus的updateById方法更新乘客信息
        return passengerMapper.updateById(passenger) > 0;
    }
}
