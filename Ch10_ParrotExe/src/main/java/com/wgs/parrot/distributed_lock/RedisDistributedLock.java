package com.wgs.parrot.distributed_lock;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RedisDistributedLock implements DistributedLock {

    /**
     * Get redis lock script.
     * param1 - KEYS[1]: redisKey
     * params2 -  ARGV[1]: clientId
     * params3 - ARGV[2]: expire time
     *
     */
    private static final String OBTAIN_LOCK_SCRIPT =
            "local lockClientId = redis.call('GET', KEYS[1])\n" +
                    "if lockClientId == ARGV[1] then\n" +
                    "  redis.call('PEXPIRE', KEYS[1], ARGV[2])\n" +
                    "  return true\n" +
                    "elseif not lockClientId then\n" +
                    "  redis.call('SET', KEYS[1], ARGV[1], 'PX', ARGV[2])\n" +
                    "  return true\n" +
                    "end\n" +
                    "return false";
    private final String clientId = UUID.randomUUID().toString();
    private final long expireTime;


    private Map<String, Object> locks = new ConcurrentHashMap<>();

    private final StringRedisTemplate redisTemplate;

    private final RedisScript<Boolean> redisScript;

    /**
     * Prefix path of a redis key, usually use project name.
     * eg: in a order service(driver-order),
     *     order pay redis key like this: driver-order:order:pay:13932032323288
     *     order cancel redis key like this: driver-order:order:cancel:13289328392932
     */
    private final String registerPath;

    public RedisDistributedLock(RedisConnectionFactory connectionFactory, String registerPath, long expireTime) {
        this.redisTemplate = new StringRedisTemplate(connectionFactory);
        this.registerPath = registerPath;
        this.redisScript = new DefaultRedisScript(OBTAIN_LOCK_SCRIPT, Boolean.class);
        this.expireTime = expireTime;
    }

    @Override
    public boolean lock(String key, String value) {
        Assert.notNull(key != null, "key should not null.");
        locks.putIfAbsent(key, null);
        return true;
    }

    @Override
    public boolean unlock(String key) {
        return false;
    }

    private final class RedisLocalLock implements Lock {
        private String lockKey;

        /**
         * Local lock, implement re-entry lock and deduct the pressure of redis.
         */
        private Lock localLock = new ReentrantLock();

        /**
         * Record the time on acquire lock to calculate the lock is expire.
         */
        private volatile long lockAcquireTime;

        public RedisLocalLock(String lockKey) {
            this.lockKey = RedisDistributedLock.this.registerPath + ":" + lockKey;
        }

        @Override
        public void lock() {
            // local lock
            localLock.lock();

            // redis lock
            boolean success = RedisDistributedLock.this.redisTemplate.execute(RedisDistributedLock.this.redisScript,
                    Collections.singletonList(this.lockKey),
                    RedisDistributedLock.this.clientId,
                    String.valueOf(RedisDistributedLock.this.expireTime));
            if (success) {
                lockAcquireTime = System.currentTimeMillis();
            }


        }

        @Override
        public void lockInterruptibly() throws InterruptedException {

        }

        @Override
        public boolean tryLock() {
            return false;
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public void unlock() {

        }

        @Override
        public Condition newCondition() {
            return null;
        }
    }

}
