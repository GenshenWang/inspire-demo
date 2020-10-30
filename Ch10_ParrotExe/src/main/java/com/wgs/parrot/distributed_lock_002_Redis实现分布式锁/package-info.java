package com.wgs.parrot.distributed_lock_002_Redis实现分布式锁;

/**
 * Redis分布式锁的实现
 *  参考Spring-data-redis中的redis组件实现
 *  本地ReentrantLock锁 + Redis lua
 *  使用自实现的分布式锁模拟秒杀场景
 */