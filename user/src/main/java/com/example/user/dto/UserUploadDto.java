package com.example.user.dto;

import lombok.Data;



@Data
public class UserUploadDto {
    private Long id; // 用户ID（主键）
    private String username; // 用户名（唯一登录标识）
    private String realName; // 真实姓名（需与身份证一致）
    private String idCard; // 身份证号（加密存储）
    private String phone; // 手机号（唯一）
    private String email; // 邮箱（可选）
    private Integer isVerified; // 实名认证状态（0未认证，1已认证）

}