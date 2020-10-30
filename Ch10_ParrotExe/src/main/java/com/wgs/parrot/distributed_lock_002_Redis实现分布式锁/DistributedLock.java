package com.wgs.parrot.distributed_lock_002_Redis实现分布式锁;

import java.util.concurrent.locks.Lock;

public interface DistributedLock {

    Lock obtainLock(String key);
}
