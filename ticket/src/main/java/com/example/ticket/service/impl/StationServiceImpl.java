package com.example.ticket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ticket.entity.Station;
import com.example.ticket.entity.TrainInfo;
import com.example.ticket.mapper.StationMapper;
import com.example.ticket.service.IStaionService;
import com.example.ticket.util.ResponseResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class StationServiceImpl extends ServiceImpl<StationMapper, TrainInfo> implements IStaionService {
    @Autowired
    RedisTemplate redisTemplate;
    private static final Logger log = LoggerFactory.getLogger(StationServiceImpl.class);
    @Override
    public ResponseResult selectById(Integer id) {
        QueryWrapper<TrainInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        log.info("");
        return ResponseResult.success(getOne(queryWrapper));
    }
    @Override
    public ResponseResult selectAll() {
        log.info("niubi");
        return ResponseResult.success(redisTemplate.opsForValue().get("AllTrains"));
}}
