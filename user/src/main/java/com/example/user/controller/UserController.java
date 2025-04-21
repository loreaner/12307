package com.example.user.controller;

import com.example.user.entity.User;
import com.example.user.service.IUserService;
import com.example.user.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    @Autowired
    IUserService userService;

    @PostMapping("/login")
    public ResponseResult login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return userService.login(username, password);
    }

    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user) {
        System.out.println(user);
        return userService.register(user);
    }

    @GetMapping("info")
    public ResponseResult selectByName(@RequestParam("username") String username) {
        return userService.selectByName(username);
    }
}