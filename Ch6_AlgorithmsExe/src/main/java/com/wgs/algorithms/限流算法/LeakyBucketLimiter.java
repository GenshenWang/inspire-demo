package com.wgs.algorithms.限流算法;

/**
 * @author: wanggenshen
 * @date: 2020/6/29 21:00.
 * @description: 漏桶限流算法
 */
public class LeakyBucketLimiter {

    /**
     * 桶内剩余的水
     */
    private long left;

    /**
     * 桶的容量
     */
    private long capacity;

    /**
     * 一桶水漏完的时间
     */
    private long duration;

    /**
     * 桶漏水的速率, capacity = duration*velocity
     */
    private double velocity;

    /**
     * 上一次成功放入水桶的时间
     */
    private long lastUpdateTime;

    public boolean acquire() {
        long now = System.currentTimeMillis();

        // 剩余的水量 - 桶匀速漏出去的水
        left = Math.max(0, left - (long)((now - lastUpdateTime) * velocity));

        // 当前水桶再加一单位水没有溢出, 则可以继续访问
        if (left++ <= capacity) {
            lastUpdateTime = now;
            return true;
        } else {
            return false;
        }
    }
}
