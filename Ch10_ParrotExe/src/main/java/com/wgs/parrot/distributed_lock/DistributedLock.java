package com.wgs.parrot.distributed_lock;

import java.util.concurrent.locks.Lock;

public interface DistributedLock {

    Lock obtainLock(String key);
}
