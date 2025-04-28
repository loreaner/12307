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
import java.util.Random;

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
        // 生成一个较小的随机id
        Random random = new Random();
        long newId = random.nextInt(1000) + 1; // 生成1到1000之间的随机数
        passenger.setId(newId);

        // 检查传入的Passenger对象是否包含有效的userName和phone
        if (passenger.getUserName() == null || passenger.getUserName().isEmpty()) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        if (passenger.getPhone() == null || passenger.getPhone().isEmpty()) {
            throw new IllegalArgumentException("手机号不能为空");
        }

        QueryWrapper<Passenger> queryWrapper = new QueryWrapper<>();

        return passengerMapper.insert(passenger) > 0;
    }

    @Override
    public boolean updatePassenger(Passenger passenger) {
        // 检查传入的Passenger对象是否包含有效的id
        if (passenger.getId() == null) {
            throw new IllegalArgumentException("乘客ID不能为空");
        }
        // 根据id查询现有乘客信息
        Passenger existingPassenger = passengerMapper.selectById(passenger.getId());
        if (existingPassenger == null) {
            throw new RuntimeException("乘客不存在");
        }
        // 更新除id外的其他字段
        existingPassenger.setUserName(passenger.getUserName());
        existingPassenger.setName(passenger.getName());
        existingPassenger.setIdType(passenger.getIdType());
        existingPassenger.setIdNumber(passenger.getIdNumber());
        existingPassenger.setPhone(passenger.getPhone());
        // 调用MyBatis-Plus的updateById方法更新乘客信息
        return passengerMapper.updateById(existingPassenger) > 0;
    }
}
