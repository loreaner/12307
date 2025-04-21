package com.example.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.user.dto.UserUploadDto;
import com.example.user.entity.User;
import com.example.user.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.user.service.IUserService;
import com.example.user.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseResult login(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        log.info("login");
        queryWrapper.eq("password_hash", password);

        // 调用 getOne 方法检查用户是否存在
        if (userMapper.selectOne(queryWrapper) != null) {
            log.info("登录成功");
            return ResponseResult.success("登录成功");
        }
        log.info("登录失败");
        return ResponseResult.error("登录失败");
    }
   @Override
    public ResponseResult register(User user) {
        // 校验用户名是否为空
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            return ResponseResult.error("用户名不能为空");
        }

        // 校验密码是否为空
        if (user.getPasswordHash() == null || user.getPasswordHash().isEmpty()) {
            return ResponseResult.error("密码不能为空");
        }

        // 校验真实姓名是否为空
        if (user.getRealName() == null || user.getRealName().isEmpty()) {
            return ResponseResult.error("真实姓名不能为空");
        }

        // 校验身份证号格式
        if (user.getIdCard() != null && !Pattern.matches("\\d{18}", user.getIdCard())) {
            return ResponseResult.error("身份证号格式不正确");
        }

        // 校验手机号格式
        if (user.getPhone() != null && !Pattern.matches("\\d{11}", user.getPhone())) {
            return ResponseResult.error("手机号格式不正确");
        }

        // 校验邮箱格式（如果邮箱不为空）
        if (user.getEmail() != null && !Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", user.getEmail())) {
            return ResponseResult.error("邮箱格式不正确");
        }
        // 检查用户名是否已存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        if (userMapper.selectOne(queryWrapper) != null) {
            return ResponseResult.error("用户名已存在");
        }

        // 检查身份证号是否已存在
        queryWrapper.clear();
        queryWrapper.eq("id_card", user.getIdCard());
        if (userMapper.selectOne(queryWrapper) != null) {
            return ResponseResult.error("身份证号已存在");
        }

        // 检查手机号是否已存在
        queryWrapper.clear();
        queryWrapper.eq("phone", user.getPhone());
        if (userMapper.selectOne(queryWrapper) != null) {
            return ResponseResult.error("手机号已存在");
        }

        // 插入新用户
        if (userMapper.insert(user) > 0) {
            return ResponseResult.success("注册成功");
        }
        return ResponseResult.error("注册失败");
    }
    @Override
    public ResponseResult selectByName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user != null) {
            UserUploadDto userUploadDto = new UserUploadDto();
            userUploadDto.setId(user.getId());
            userUploadDto.setUsername(user.getUsername());
            userUploadDto.setRealName(user.getRealName());
            userUploadDto.setIdCard(user.getIdCard());
            userUploadDto.setPhone(user.getPhone());
            userUploadDto.setEmail(user.getEmail());
            userUploadDto.setIsVerified(user.getIsVerified());

            return ResponseResult.success("用户名已存在", userUploadDto);
        }
        return ResponseResult.error("用户名不存在");
    }
}