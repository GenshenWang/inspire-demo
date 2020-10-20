package com.wgs.parrot.distributed_lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.Lock;

@RestController
public class RedisDistributedLockTest {

    @Autowired
    @Qualifier(value = "redisDistributedLock")
    private DistributedLock distributedLock;


    private static final String REDIS_KEY = "MIAOSHA_GOOD_ID";

    /**
     * 使用分布式锁模拟秒杀场景
     * 当商品goodId被用户1锁定时，用户2购买就会返回失败
     *
     * http://localhost:8080/buy?goodId=12126&userId=889906
     * @param goodId
     * @param userId
     * @return
     */
    @GetMapping("/buy")
    public String miaoSha(@RequestParam("goodId") long goodId,
                      @RequestParam("userId") long userId) {

        System.out.println("秒杀活动开始了，用户【" + userId +"】尝试抢商品" + goodId);
        Lock lock = distributedLock.obtainLock(REDIS_KEY + goodId);
        lock.lock();
        try {
            System.out.println("===> 用户【" + userId + "】抢到了商品：" + goodId);
            Thread.sleep(10000);
        } catch (Exception e) {
            // ...
        } finally {
            lock.unlock();
        }

        System.out.println("用户【" + userId + "】付款成功， 商品数量-1 " + goodId);
        return "用户" + userId + "剁手了100万";

    }
}
