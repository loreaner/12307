package com.example.ticket.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;


import com.example.ticket.entity.Station;
import com.example.ticket.entity.TrainInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StationMapper extends BaseMapper<TrainInfo> {


}
