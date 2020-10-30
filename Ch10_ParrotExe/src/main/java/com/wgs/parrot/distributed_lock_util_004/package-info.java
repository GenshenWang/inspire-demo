package com.wgs.parrot.distributed_lock_util_004;

/**
 * 封装分布式锁工具的实现
 * 支持自定义分布式锁，如Redis、ZK、MySQL
 * 支持自定义加锁成功或者失败的场景；
 * 支持降级切换：当Redis加锁失败时，
 */