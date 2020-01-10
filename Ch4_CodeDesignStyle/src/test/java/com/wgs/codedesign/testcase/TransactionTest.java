package com.wgs.codedesign.testcase;

import com.wgs.codedesign.Ch4CodeDesignStyleApplication;
import com.wgs.codedesign.testcase.service.MockTransactionRedisDistributedLock;
import com.wgs.codedesign.testcase.service.RedisDistributedLock;
import com.wgs.codedesign.testcase.service.STATUS;
import com.wgs.codedesign.testcase.service.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: Created by wanggenshen
 * @date: 2020/1/10 19:41.
 * @description: XXX
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Ch4CodeDesignStyleApplication.class)
public class TransactionTest {

    private static final long expireTime = 14 * 24 * 60 * 60 * 1000;

    /**
     * 转账失败
     */
    @Test
    public void testTractioning() {
        Transaction transaction = new Transaction("123", 12323L, 233454L, 8737478L, 12.32, "328738234623747orderid");
        MockFailWalletRpcService failWalletRpcService  = new MockFailWalletRpcService();
        MockTransactionRedisDistributedLock mockLockService = new MockTransactionRedisDistributedLock() {
            public boolean lock(String id) {
                return true;
            }
            public void unlock(String id) {

            }
        };
        transaction.setWalletRpcService(failWalletRpcService);
        transaction.setLock(mockLockService);

        boolean executeResult = false;
        try {
            executeResult = transaction.execute();
        } catch (Exception e) {
            e.printStackTrace();
            executeResult = false;
        }
        Assert.assertTrue(executeResult == false);
        Assert.assertTrue(transaction.getStatus().equals(STATUS.FAILED.getCode()));
    }


    /**
     * 正在交易中(分布式锁失败)
     */
    @Test
    public void testLockFail() {
        Transaction transaction = new Transaction("123", 12323L, 233454L, 8737478L, 12.32, "328738234623747orderid");
        MockSuccessWalletRpcService successWalletRpcService = new MockSuccessWalletRpcService();

        // 锁失败(表示正在交易)
        MockTransactionRedisDistributedLock mockLockService = new MockTransactionRedisDistributedLock() {
            public boolean lock(String id) {
                return false;
            }

            public void unlock(String id) {

            }
        };
        transaction.setWalletRpcService(successWalletRpcService);
        transaction.setLock(mockLockService);

        boolean executeResult = false;
        try {
            executeResult = transaction.execute();
        } catch (Exception e) {
            e.printStackTrace();
            executeResult = false;
        }
        Assert.assertTrue(executeResult == false);
    }

    /**
     * 交易过期
     */
    @Test
    public void testTransactionExpire() {
        Transaction transaction = new Transaction("123", 12323L, 233454L, 8737478L, 12.32, "328738234623747orderid") {
            protected boolean isExpired(long executionInvokedTimestamp) {
                return true;
            }
        };
        MockSuccessWalletRpcService successWalletRpcService = new MockSuccessWalletRpcService();
        MockTransactionRedisDistributedLock mockLockService = new MockTransactionRedisDistributedLock();
        transaction.setWalletRpcService(successWalletRpcService);
        transaction.setLock(mockLockService);

        boolean executeResult = false;
        try {
            executeResult = transaction.execute();
        } catch (Exception e) {
            e.printStackTrace();
            executeResult = false;
        }
        Assert.assertTrue(executeResult == false);
        Assert.assertTrue( transaction.getStatus() == STATUS.EXPIRED.getCode());

    }

    private Transaction generateTransaction() {
        Transaction transaction = new Transaction("123", 12323L, 233454L, 8737478L, 12.32, "328738234623747orderid");
        MockSuccessWalletRpcService successWalletRpcService = new MockSuccessWalletRpcService();
        MockTransactionRedisDistributedLock mockLockService = new MockTransactionRedisDistributedLock();
        transaction.setWalletRpcService(successWalletRpcService);
        transaction.setLock(mockLockService);

        return transaction;
    }

    /**
     * 测试正常转账流程
     */
    @Test
    public void testExecute1() {
        MockSuccessWalletRpcService successWalletRpcService = new MockSuccessWalletRpcService();
        MockTransactionRedisDistributedLock mockLockService = new MockTransactionRedisDistributedLock() {
            public boolean lock(String id) {
                return true;
            }

            public void unlock(String id) {
            }
        };
        Transaction transaction = new Transaction("123", 12323L, 233454L, 8737478L, 12.32, "328738234623747orderid");
        transaction.setWalletRpcService(successWalletRpcService);
        transaction.setLock(mockLockService);

        boolean executeResult = false;
        try {
            executeResult = transaction.execute();
        } catch (Exception e) {
            e.printStackTrace();
            executeResult = false;
        }
        Assert.assertTrue(executeResult == true);
        Assert.assertTrue( transaction.getStatus() == STATUS.EXECUTED.getCode());
        Assert.assertTrue( transaction.getWalletTransactionId().equals("success"));
        System.out.println(executeResult);
    }
}
