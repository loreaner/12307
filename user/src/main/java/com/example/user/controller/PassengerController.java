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

import static com.example.user.util.ResponseResult.success;

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
            return success("更新成功");
        } else {
            return ResponseResult.error("更新失败");
        }
    }

    @GetMapping("/search")
    public ResponseResult<List<Passenger>> searchPassengers(@RequestParam String name) {
        log.info(name+"6666");
        return success(passengerService.searchPassengersByName(name));
    }

    @PostMapping("/add")
    public ResponseResult<List<Passenger>> addPassenger(@RequestBody Passenger passenger) {
        log.info(String.valueOf(passenger));
        if(passengerService.addPassenger(passenger)==false){
            return ResponseResult.error("该乘客已存在");
        }
        return  success(passengerService.searchPassengersByName(passenger.getUserName()));

    }

    @PostMapping("/delete")
    public ResponseResult batchDeletePassengers(@RequestBody List<Long> ids) {
        if(passengerService.batchDeletePassengers(ids)>0)
            return ResponseResult.success("删除成功");
        return ResponseResult.error("删除失败");
    }
}