package com.example.user.controller;

import com.example.user.entity.Passenger;
import com.example.user.service.IPassengerService;
import com.example.user.service.impl.UserServiceImpl;
import com.example.user.util.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/passenger")
@RestController
public class PassengerController {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    IPassengerService passengerService;

    @PostMapping("/update")
    public ResponseResult updatePassenger(@RequestBody Passenger passenger) {
        log.info("更新乘客信息: {}", passenger);
        boolean success = passengerService.updatePassenger(passenger);
        log.info(String.valueOf(success));
        if (success) {
            return ResponseResult.success("更新成功");
        } else {
            return ResponseResult.error("更新失败");
        }
    }

    @GetMapping("/search")
    public ResponseResult<List<Passenger>> searchPassengers(@RequestParam String name) {
        log.info(name+"6666");
        return ResponseResult.success(passengerService.searchPassengersByName(name));
    }

    @PostMapping("/add")
    public boolean addPassenger(@RequestBody Passenger passenger) {
        log.info(String.valueOf(passenger));
        return  passengerService.addPassenger(passenger);

    }

    @PostMapping("/delete")
    public int batchDeletePassengers(@RequestBody List<Long> ids) {
        return passengerService.batchDeletePassengers(ids);
    }
}