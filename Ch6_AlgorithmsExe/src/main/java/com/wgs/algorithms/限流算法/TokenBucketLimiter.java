package com.wgs.algorithms.限流算法;

/**
 * @author: wanggenshen
 * @date: 2020/6/29 21:00.
 * @description: 令牌桶限流算法
 */
public class TokenBucketLimiter {

    /**
     * 令牌桶桶内剩余的令牌
     */
    private long left;

    /**
     * 令牌桶的容量
     */
    private long capacity;

    /**
     * 一桶水漏完的时间
     */
    private long duration;

    /**
     * 令牌桶生产令牌的速率, capacity = duration*velocity
     */
    private double velocity;

    /**
     * 上一次拿走令牌的时间
     */
    private long lastUpdateTime;

    public boolean acquire() {
        long now = System.currentTimeMillis();

        // 令牌桶余量 =  【上一次令牌桶剩余的令牌】+ 【(上一次拿走令牌到现在的时间段) * 每个单位时间生产令牌的速率 】
        // 生产出的令牌 超过令牌桶的容量时, 则舍弃
        left = Math.min(capacity, left + (long)((now - lastUpdateTime) * velocity));

        // 若当前能够成功领取令牌, 则可以访问
        if (left-- >= 0) {
            lastUpdateTime = now;
            return true;
        } else {
            return false;
        }
    }
}
