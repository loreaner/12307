package com.example.ticket.entity;

import lombok.Data;

@Data
public class Station {
    private Long id; // 主键ID
    private String name; // 车站名称（如北京南站）
    private String city; // 所属城市（如北京市）
    private String pinyinCode; // 拼音缩写（如BJS）
    private Double latitude; // 纬度
    private Double longitude; // 经度

}
