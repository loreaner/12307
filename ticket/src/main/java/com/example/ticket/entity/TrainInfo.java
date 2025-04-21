package com.example.ticket.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@TableName("train")
@Data
public class TrainInfo {
    private Long id; // 主键ID
    private String trainNo; // 列车编号（如G101）
    private String trainType; // 列车类型（如高铁）
    private String startStation; // 起始站（如北京南）
    private String endStation; // 终点站（如上海虹桥）
    private String departureTime; // 出发时间（如08:00:00）
    private String arrivalTime; // 到达时间（如13:30:00）
    private String duration; // 持续时间（如05:30:00）
    private Integer status; // 状态（如1）
}