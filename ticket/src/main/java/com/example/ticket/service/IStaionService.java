package com.example.ticket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ticket.entity.Station;
import com.example.ticket.entity.TrainInfo;
import com.example.ticket.util.ResponseResult;

public interface IStaionService extends IService<TrainInfo> {
    public ResponseResult selectAll();
    public ResponseResult selectById(Integer id);
}
