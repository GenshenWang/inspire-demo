package com.wgs.sentinel.v2.algorithm;

import com.google.common.base.Stopwatch;
import com.wgs.sentinel.v1.algorithm.*;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: wanggenshen
 * @date: 2020/6/23 23:58.
 * @description: 固定时间窗口算法统计接口请求流量
 */
public class FixedWindowRateLimitAlg implements RateLimitAlg {
    // ms
    private static final long LOCK_EXPIRE_TIME = 200L;

    private Stopwatch stopWatch;
    // 限流计数器
    private AtomicInteger counter = new AtomicInteger(0);
    private final int limit;
    private Lock lock = new ReentrantLock();

    public FixedWindowRateLimitAlg(int limit) {
        this(limit, Stopwatch.createStarted());
    }

    public FixedWindowRateLimitAlg(int limit, Stopwatch stopWatch) {
        this.limit = limit;
        this.stopWatch = stopWatch;
    }

    @Override
    public boolean tryAcquire() throws InterruptedException {
        int currentCount = counter.incrementAndGet();
        // 未达到限流
        if (currentCount < limit) {
            return true;
        }

        // 使用固定时间窗口统计当前窗口请求数
        // 请求到来时,加锁进行计数器统计工作
        try {
            if (lock.tryLock(LOCK_EXPIRE_TIME, TimeUnit.MILLISECONDS)) {
                // 如果超过这个时间窗口, 则计数器counter归零, stopWatch, 窗口进入下一个窗口
                if (stopWatch.elapsed(TimeUnit.MILLISECONDS) > TimeUnit.SECONDS.toMillis(1)) {
                    counter.set(0);
                    stopWatch.reset();
                }

                // 不超过, 则当前时间窗口内的计数器counter+1
                currentCount = counter.incrementAndGet();
                return currentCount < limit;
            }
        } catch (InterruptedException e) {
            System.out.println("tryAcquire() wait lock too long:" + LOCK_EXPIRE_TIME + " ms");
            throw new InterruptedException("tryAcquire() wait lock too long:" + LOCK_EXPIRE_TIME + " ms");
        } finally {
            lock.unlock();
        }

        // 出现异常 不能影响接口正常请求
        return true;
    }


}
