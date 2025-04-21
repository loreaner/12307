package com.example.user.entity;

import lombok.Data;
import java.util.Date;
@Data
public class User {
    private Long id; // 用户ID（主键）
    private String username; // 用户名（唯一登录标识）
    private String passwordHash; // 加密后的密码（如BCrypt）
    private String realName; // 真实姓名（需与身份证一致）
    private String idCard; // 身份证号（加密存储）
    private String phone; // 手机号（唯一）
    private String email; // 邮箱（可选）
    private Integer isVerified; // 实名认证状态（0未认证，1已认证）
    private Date createdAt; // 注册时间
    private Date updatedAt; // 最后更新时间

}