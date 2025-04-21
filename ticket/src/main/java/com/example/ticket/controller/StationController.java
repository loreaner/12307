package com.example.ticket.controller;

import com.example.ticket.service.IStaionService;
import com.example.ticket.util.ResponseResult;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("ticket")
@RestController
public class StationController {
    @Autowired
    IStaionService staionService;

    @GetMapping("selectById")
    public ResponseResult selectByName(Integer id) {
        return staionService.selectById(id);
    }

    @GetMapping("selectAll")
    public ResponseResult selectAll() {
        return staionService.selectAll();
    }
}
