package com.example.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.user.entity.Passenger;
import com.example.user.util.ResponseResult;

import java.util.List;

public interface IPassengerService extends IService<Passenger> {
    public int batchDeletePassengers(List<Long> ids );
    public List<Passenger>  searchPassengersByName(String name);
    public boolean addPassenger(Passenger passenger);

    public boolean updatePassenger(Passenger passenger);
}
