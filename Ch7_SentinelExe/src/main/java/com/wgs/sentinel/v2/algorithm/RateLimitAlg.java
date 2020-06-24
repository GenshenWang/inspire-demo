package com.wgs.sentinel.v2.algorithm;

/**
 * @author: wanggenshen
 * @date: 2020/6/23 23:56.
 * @description: 限流算法: 固定时间窗口、滑动窗口、令牌桶等
 */
public interface RateLimitAlg {

    boolean tryAcquire() throws InterruptedException;
}
