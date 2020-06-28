package com.wgs.sentinel.v2.limiter;

import com.wgs.sentinel.v2.algorithm.FixedWindowRateLimitAlg;
import com.wgs.sentinel.v2.algorithm.RateLimitAlg;
import com.wgs.sentinel.v2.datasource.FileRuleConfigSource;
import com.wgs.sentinel.v2.datasource.RuleConfigSource;
import com.wgs.sentinel.v2.rule.ApiLimit;
import com.wgs.sentinel.v2.rule.RateLimitRule;
import com.wgs.sentinel.v2.rule.RuleConfig;
import org.springframework.util.Assert;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: wanggenshen
 * @date: 2020/6/24 00:00.
 * @description: 接口限流类
 */
public class RateLimiter {

    private RateLimitRule rule;
    // 每个API内存中存储限流计数器, key为 api:url
    private ConcurrentHashMap<String, RateLimitAlg> counters = new ConcurrentHashMap<>();

    private RuleConfigSource ruleConfigSource;

    public RateLimiter() {
        ruleConfigSource = new FileRuleConfigSource();
        RuleConfig ruleConfig = ruleConfigSource.load();
        Assert.isTrue(ruleConfig != null, "Load from yaml file, RuleConfig is null");
        this.rule = new RateLimitRule(ruleConfig);
    }


    /**
     * 判断接口是否限流
     *
     * @param appId
     * @param url
     * @return true: 不限流; false: 限流
     * @throws InterruptedException
     */
    public boolean limit(String appId, String url) throws InterruptedException {
        // 接口未配置限流, 直接返回
        ApiLimit apiLimit = rule.getApiLimit(appId, url);
        if (apiLimit == null) {
            return true;
        }

        String counterKey = appId + ":" + url;
        RateLimitAlg rateLimitAlg = counters.get(counterKey);
        if (rateLimitAlg == null) {
            // 没有计数器, 就构造一个
            RateLimitAlg rateLimitCounterNew = new FixedWindowRateLimitAlg(apiLimit.getLimit());
            RateLimitAlg rateLimitCounterOld = counters.putIfAbsent(counterKey, rateLimitCounterNew);
            if (rateLimitCounterOld == null) {
                rateLimitAlg = rateLimitCounterNew;
            }
        }

        // 固定窗口统计, 判断是否超过限流阈值
        return rateLimitAlg.tryAcquire();

    }


    public static void main(String[] args) {
        RateLimiter rateLimiter = new RateLimiter();
        try {
            for (int i = 0; i < 10; i++) {
                boolean b = rateLimiter.limit("app1", "/v1/user");
                System.out.println("/v1/user接口限流结果: " + b);
            }

            System.out.println("=====");

            for (int i = 0; i < 10; i++) {
                boolean b = rateLimiter.limit("app1", "/v1/order");
                System.out.println("/v1/order接口限流结果: " + b);
            }

            System.out.println("=====");
            for (int i = 0; i < 10; i++) {
                boolean b = rateLimiter.limit("app2", "/v1/login");
                System.out.println("/v1/login接口限流结果:" + b);
            }
        } catch (Exception e) {

        }
    }
}