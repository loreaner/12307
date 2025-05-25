package com.example.ticket.schduler;

import com.example.ticket.entity.TrainInfo;
import com.example.ticket.mapper.StationMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RedisScheduler {

    private static final Logger logger = LoggerFactory.getLogger(RedisScheduler.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    StationMapper  stationMapper;
    // 每隔一小时执行一次
    @Scheduled(cron = "0 0 * * * ?", zone = "Asia/Shanghai")
    public void scheduledTask() {
        // 这里可以添加需要定时执行的逻辑
        logger.info("定时任务执行中...");
        System.out.println("定时任务执行中...");
    }
    @Scheduled(cron = "0 * * * * ?", zone = "Asia/Shanghai")
    // 存储全部车次数据到 Redis
    public void storeAllTrains() {
        List<TrainInfo> currentValue = (List<TrainInfo>) redisTemplate.opsForValue().get("AllTrains");
        List<TrainInfo> trains = stationMapper.selectList(null);
 // 2. 比较新值（假设 trains 是 String 类型）
        if (!trains.equals(currentValue)) {
            // 3. 只有值不同时才更新
            redisTemplate.opsForValue().set("AllTrains", trains);
            logger.info("全部车次数据已存储到 Redis，键为: {}", trains);
        }

    }
    @Scheduled(cron = "0 * * * * ?", zone = "Asia/Shanghai")
// 使用 Hash 结构存储全部车次数据到 Redis
    public void storeAllTrains2() {
        // 1. 获取当前 Redis 中的 Hash 数据
        Map<Object, Object> currentHashValue = redisTemplate.opsForHash().entries("region_train_station:北京_杭");

        // 2. 从数据库中获取最新的车次数据
        List<TrainInfo> trains = stationMapper.selectList(null);

        // 3. 准备新的 Hash 数据
        Map<String, String> newHashData = new HashMap<>();
        for (TrainInfo train : trains) {
            String key = train.getDepartureTime() + "_" + train;
            JSONPObject JSONPObject = new JSONPObject("callback", train);
            String value = JSONPObject.toString(); // 假设使用了 FastJSON 或 Jackson 进行 JSON 转换
            newHashData.put(key, value);
        }

        // 4. 比较新旧数据，如果不同则更新 Redis 中的 Hash 数据
        if (!isHashDataEqual(currentHashValue, newHashData)) {
            // 更新 Redis 中的 Hash 数据
            redisTemplate.opsForHash().putAll("region_train_station:北京_杭", newHashData);
            logger.info("全部车次数据已更新到 Redis Hash，键为: region_train_station:北京_杭");
        } else {
            logger.info("Redis Hash 数据未发生变化，无需更新");
        }
    }

    private boolean isHashDataEqual(Map<Object, Object> currentHashValue, Map<String, String> newHashData) {
        // 将 currentHashValue 转换为 Map<String, String> 以便比较
        Map<String, String> currentData = new HashMap<>();
        for (Map.Entry<Object, Object> entry : currentHashValue.entrySet()) {
            currentData.put(entry.getKey().toString(), entry.getValue().toString());
        }

        // 比较两个 Map 是否相等
        return currentData.equals(newHashData);
    }

    @Scheduled(cron = "0 * * * * ?", zone = "Asia/Shanghai")
    // 动态生成 key，格式为 "region_train_station:起始城市_终点城市_日期"
    public void storeAllTrains3() {
        String prefix = "region_train_station";
        String dynamicPart = getDynamicPart(); // 假设这是一个获取动态部分的方法
        String redisKey = prefix + ":" + dynamicPart;

        // 1. 获取当前 Redis 中的 Hash 数据
        Map<Object, Object> currentHashValue = redisTemplate.opsForHash().entries(redisKey);

        // 2. 从数据库中获取最新的车次数据
        List<TrainInfo> trains = stationMapper.selectList(null);

        // 3. 准备新的 Hash 数据
        Map<String, String> newHashData = new HashMap<>();
        for (TrainInfo train : trains) {
            // Hash 结构中的 key 生成逻辑，格式为 "列车ID_起始站点_终点"
            String hashKey = train.getTrainNo() + "_" + train.getStartStation() + "_" + train.getEndStation();
            JSONPObject JSONPObject = new JSONPObject("callback", train);
            String value = JSONPObject.toString(); // 假设使用了 FastJSON 或 Jackson 进行 JSON 转换
            newHashData.put(hashKey, value);
        }

        // 4. 比较新旧数据，如果不同则更新 Redis 中的 Hash 数据
        if (!isHashDataEqual(currentHashValue, newHashData)) {
            // 更新 Redis 中的 Hash 数据
            redisTemplate.opsForHash().putAll(redisKey, newHashData);
            logger.info("全部车次数据已更新到 Redis Hash，键为: {}", redisKey);
        } else {
            logger.info("Redis Hash 数据未发生变化，无需更新");
        }
    }

    private String getDynamicPart() {
        // 这里可以添加获取动态部分的逻辑，例如从配置文件、数据库或其他来源获取
        return "北京_杭_2023-10-01";
    }
}