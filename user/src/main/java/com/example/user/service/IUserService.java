package com.example.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.user.entity.User;
import com.example.user.util.ResponseResult;

public interface IUserService extends IService<User> {
    public ResponseResult login(String username, String password);
    public ResponseResult register(User user);
    public ResponseResult selectByName(String username);

}
