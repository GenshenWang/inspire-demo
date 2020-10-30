package com.wgs.parrot.distributed_lock_002_Redis实现分布式锁;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RedisDistributedLock implements DistributedLock {

    private static final Logger logger = LoggerFactory.getLogger(RedisDistributedLock.class);
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
    private final Map<String, RedisLocalLock> locks = new ConcurrentHashMap<>();
    private final StringRedisTemplate redisTemplate;
    private final RedisScript<Boolean> redisScript;
    private Executor executorService = Executors.newFixedThreadPool(2, new CustomerThreadFactory("Redis-Lock-Executor"));

    static class CustomerThreadFactory implements ThreadFactory {

        private String name;

        public CustomerThreadFactory(String name) {
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread();
        }
    }

    /**
     * Prefix path of a redis key, usually use project name.
     * eg: in a order service(driver-order),
     *     order pay redis key like this: driver-order:order:pay:13932032323288
     *     order cancel redis key like this: driver-order:order:cancel:13289328392932
     */
    private final String registerPath;

    public RedisDistributedLock(RedisConnectionFactory connectionFactory, String registerPath) {
        this(connectionFactory, registerPath, 0);
    }

    public RedisDistributedLock(RedisConnectionFactory connectionFactory, String registerPath, long expireTime) {
        this.redisTemplate = new StringRedisTemplate(connectionFactory);
        this.registerPath = registerPath;
        this.redisScript = new DefaultRedisScript(OBTAIN_LOCK_SCRIPT, Boolean.class);
        this.expireTime = expireTime;
    }

    public void setExecutor(Executor executor) {
        this.executorService = executor;
    }

    @Override
    public Lock obtainLock(String key) {
        Assert.notNull(key != null, "key should not null.");
        return this.locks.computeIfAbsent(key, RedisLocalLock::new);
    }

    private final class RedisLocalLock implements Lock {
        private String lockKey;

        /**
         * Local lock, implement re-entry lock and deduct the pressure of redis.
         */
        private final ReentrantLock localLock = new ReentrantLock();

        /**
         * Record the time on acquire lock to calculate the lock is expire.
         */
        private volatile long lockAcquireTime;

        public RedisLocalLock(String lockKey) {
            this.lockKey = RedisDistributedLock.this.registerPath + ":" + lockKey;
        }

        /**
         * Assumed thread-A obtain lock, thread-B try acquire lock will wait
         * and cannot be interrupted, if use thread-B.interrupt() method, will wait until it acquired lock.
         */
        @Override
        public void lock() {
            // local lock
            localLock.lock();
            while (true) {
                try {
                    // redis lock
                    while (!obtainLock()) {
                        Thread.sleep(100);
                    }

                    // acquired lock then break while
                    break;
                } catch (InterruptedException e) {
                    //..
                } catch (Exception e) {
                    this.localLock.unlock();
                    throw new RuntimeException("Cannot acquire lock " + this.lockKey + ", cause="  +e.getMessage());
                }
            }
        }

        private boolean obtainLock() {
            boolean success = RedisDistributedLock.this.redisTemplate.execute(RedisDistributedLock.this.redisScript,
                    Collections.singletonList(this.lockKey),
                    RedisDistributedLock.this.clientId,
                    String.valueOf(RedisDistributedLock.this.expireTime));
            if (success) {
                lockAcquireTime = System.currentTimeMillis();
            }
            return success;
        }

        /**
         *  Assumed thread-A obtain lock,
         *  while thread-B try acquiring lock, if use threadB.interrupter, will occur  InterruptedException,
         *  then unlock will throws java.lang.IllegalMonitorStateException because the lock is not held by threadB.
         *
         * @throws InterruptedException
         */
        @Override
        public void lockInterruptibly() throws InterruptedException {
            this.localLock.lockInterruptibly();
            try {
                while (!obtainLock()) {
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                this.localLock.unlock();
                Thread.currentThread().interrupt();
                throw e;
            } catch (Exception e) {
                this.localLock.unlock();
                throw new RuntimeException("lock interruptibly exception: " + e.toString());
            }

        }

        /**
         * If lock is holding by thread-A, thread-B try acquire lock will return false, else return true.
         * @return
         */
        @Override
        public boolean tryLock() {
            try {
                return this.tryLock(0, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.interrupted();
                return false;
            }
        }

        /**
         * If lock is holding by thread-A,
         * thread-B will try acquire lock while whole unit time.
         *
         * @param time
         * @param unit
         * @return
         * @throws InterruptedException
         */
        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            boolean lockSuccess = this.localLock.tryLock(time, unit);
            if (!lockSuccess) {
                return false;
            }

            long now = System.currentTimeMillis();
            long expire = now + TimeUnit.SECONDS.convert(time, unit);
            try {
                boolean acquired;
                while (!(acquired = obtainLock()) && System.currentTimeMillis() < expire) {
                    Thread.sleep(100);
                }
                // if in time can't acquire lock, then unlock
                if (!acquired) {
                    localLock.unlock();
                }
                return acquired;

            } catch (Exception e) {
                this.localLock.unlock();
                throw new RuntimeException(e.toString());
            }

        }

        /**
         * lock unlock.
         *
         */
        @Override
        public void unlock() {
            if (!localLock.isHeldByCurrentThread()) {
                throw new IllegalStateException("Lock is not held by current thread");
            }

            // if lock hold count > 1, local lock -1.
            if (localLock.getHoldCount() > 1) {
                localLock.unlock();
                return;
            }

            try {
                if (Thread.currentThread().isInterrupted()) {
                    executorService.execute(() -> RedisDistributedLock.this.redisTemplate.delete(lockKey));
                } else {
                    RedisDistributedLock.this.redisTemplate.delete(lockKey);
                }
                logger.info("Success delete lockKey:{}", lockKey);

            } catch (Exception e) {
                throw new RuntimeException(e.toString());
            } finally {
                this.localLock.unlock();
            }

        }

        @Override
        public Condition newCondition() {
            throw new UnsupportedOperationException("Condition is not supported.");
        }
    }

}
