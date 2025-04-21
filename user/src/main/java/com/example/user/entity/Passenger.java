package com.example.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Passenger {
    private Long id;
    @TableField(value = "username")
    private String userName;
    private String name;
    private String idType;
    private String idNumber;
    private String phone;

    // 其他必要字段
}