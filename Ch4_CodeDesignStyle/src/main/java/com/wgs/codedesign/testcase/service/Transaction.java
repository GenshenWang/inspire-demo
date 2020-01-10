package com.wgs.codedesign.testcase.service;

import lombok.Getter;

/**
 * @author: Created by wanggenshen
 * @date: 2020/1/10 16:36.
 * @description: XXX
 */
@Getter
public class Transaction {
    private String id;
    private Long buyerId;
    private Long sellerId;
    private Long productId;
    private String orderId;
    private Long createTimestamp;
    private Double amount;
    private Integer status;
    private String walletTransactionId;

    private WalletRpcService walletRpcService;
    private MockTransactionRedisDistributedLock lock;

    public void setWalletRpcService(WalletRpcService walletRpcService) {
        this.walletRpcService = walletRpcService;
    }

    public void setLock(MockTransactionRedisDistributedLock lock) {
        this.lock = lock;
    }

    private static final long expireTime = 14 * 24 * 60 * 60 * 1000;

    public Transaction(String preAssignedId, Long buyerId, Long sellerId, Long productId, Double amount, String orderId) {
        if (preAssignedId != null && !preAssignedId.isEmpty()) {
            this.id = preAssignedId;
        } else {
            this.id = IdGenerator.generateTransactionId();
        } if (!this.id.startsWith("t_")) {
            this.id = "t_" + preAssignedId;
        }
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.productId = productId;
        this.orderId = orderId;
        this.status = STATUS.UN_EXECUTED.code;
        this.amount = amount;
        this.createTimestamp = System.currentTimeMillis();
    }

    public boolean execute() throws Exception {
        if ((buyerId == null || sellerId == null || amount < 0.0)) {
            throw new Exception("invalid params");
        }
        if (status == STATUS.EXECUTED.code) {
            return true;
        }
        boolean isLocked = false;
        try {
            isLocked = lock.lock(id);
            if (!isLocked) {
                // 锁定未成功，返回false，job兜底执行
                return false;
            }
            if (status == STATUS.EXECUTED.code) {
                // double check
                return true;
            }
            long executionInvokedTimestamp = System.currentTimeMillis();
            if (isExpired(executionInvokedTimestamp)) {
                this.status = STATUS.EXPIRED.code;
                return false;
            }

            String walletTransactionId = walletRpcService.moveMoney(id, buyerId, sellerId, amount);
            if (walletTransactionId != null) {
                this.walletTransactionId = walletTransactionId;
                this.status = STATUS.EXECUTED.code;
                return true;
            } else {
                this.status = STATUS.FAILED.code;
                return false;
            }
        } finally {
            if (isLocked) {
                lock.unlock(id);
            }
        }
    }

    protected boolean isExpired(long executionInvokedTimestamp) {
        return executionInvokedTimestamp > createTimestamp + expireTime;
    }



}
