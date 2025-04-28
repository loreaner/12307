package com.example.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("train_tickets") // 指定数据库表名
public class OrderData {
    private String trainNo;
    private String date;
    private String departureStation;
    private String arrivalStation;
    private String departureTime;
    private String arrivalTime;
    private String ticketType;
    private String seatType;
    private String name;
    private String idType;
    private String idNumber;
}