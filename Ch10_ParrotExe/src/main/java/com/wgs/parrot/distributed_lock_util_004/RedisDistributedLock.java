package com.wgs.parrot.distributed_lock_util_004;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * 封装Redission API实现Redis分布式锁
 */
public class RedisDistributedLock implements DistributedLock {

    private RedissonClient redissonClient;

    public RedisDistributedLock(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public boolean lock(String key, int waitTime, TimeUnit timeUnit) {
        RLock lock = redissonClient.getLock(key);
        if (lock == null) {
            throw new RuntimeException("Failed get redis lock " + key);
        }

        boolean tryLock = false;
        try {
            tryLock = lock.tryLock(waitTime, timeUnit);
        } catch (InterruptedException e) {
            tryLock = false;
        } finally {
            if (tryLock) {
                lock.unlock();
            }
        }

        return tryLock;
    }

    @Override
    public void lock(String key, int waitTime, TimeUnit timeUnit, Runnable success, Runnable fail) {
        RLock lock = redissonClient.getLock(key);
        if (lock == null) {
            throw new RuntimeException("Failed get redis lock " + key);
        }
        boolean tryLock = false;
        try {
            tryLock = lock.tryLock(waitTime, 0, timeUnit);
            if (!tryLock) {
                fail.run();
                return;
            }
            success.run();
        } catch (InterruptedException e) {
            tryLock = false;
            fail.run();
        } finally {
            if (tryLock) {
                lock.unlock();
            }
        }

    }

    @Override
    public void unlock(String key) {
        RLock lock = redissonClient.getLock(key);
        if (lock == null) {
            throw new RuntimeException("Failed get redis lock " + key);
        }
        lock.unlock();
    }
}
