package com.wgs.parrot.distributed_lock;

public interface DistributedLock {

    boolean lock(String key, String value);

    boolean unlock(String key);
}
