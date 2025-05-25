package com.example.ticket;

import com.example.ticket.mapper.StationMapper;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class TicketApplicationTests {
    @Autowired
    StationMapper stationMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Test
    void contextLoads() {
        stationMapper.selectList(null);
        redisTemplate.opsForValue().get("AllTrains");

        // 正式测试（多次运行取平均值）
        int iterations = 1000;
        long totalMysqlTime = 0;
        long totalRedisTime = 0;

        for (int i = 0; i < iterations; i++) {
            // MySQL 测试
            long startMysql = System.nanoTime();
            stationMapper.selectList(null);
            long endMysql = System.nanoTime();
            totalMysqlTime += (endMysql - startMysql);

            // Redis 测试
            long startRedis = System.nanoTime();
            redisTemplate.opsForValue().get("AllTrains");
            long endRedis = System.nanoTime();
            totalRedisTime += (endRedis - startRedis);
        }

        // 计算平均耗时（纳秒转毫秒）
        double avgMysqlTime = totalMysqlTime / (iterations * 1_000_000.0);
        double avgRedisTime = totalRedisTime / (iterations * 1_000_000.0);

        System.out.println("MySQL 平均耗时: " + avgMysqlTime + "ms");
        System.out.println("Redis 平均耗时: " + avgRedisTime + "ms");
    }
    }

