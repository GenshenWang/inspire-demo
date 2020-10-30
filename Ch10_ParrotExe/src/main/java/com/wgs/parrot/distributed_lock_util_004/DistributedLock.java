package com.wgs.parrot.distributed_lock_util_004;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁工具类
 * 支持Redis、MySQL、ZK
 */
public interface DistributedLock {

    /**
     * 获取锁，需要手动调用unlock()进行释放锁
     *
     * @param key
     * @param waitTime  获取锁的最大时间。超过waitTime未获取到失败
     * @param timeUnit
     */
    boolean lock(String key, int waitTime, TimeUnit timeUnit);

    /**
     * 释放锁
     *
     * @param key
     */
    void unlock(String key);

    /**
     * 获取锁成功会自动释放锁
     *
     * @param key
     * @param waitTime
     * @param timeUnit
     * @param success
     * @param fail
     */
    void lock(String key, int waitTime, TimeUnit timeUnit, Runnable success, Runnable fail);
}
