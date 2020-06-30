package com.wgs.idempotent.v1;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author: wanggenshen
 * @date: 2020/6/30 21:27.
 * @description: 幂等框架
 *               (1) 生成幂等号
 *               (2) 接收请求,保存幂等号
 *               (3) 判断请求是否重复
 */
public class IdempotenceService {

    private JedisCluster jedisCluster;

    public IdempotenceService(String redisClusterAddress, GenericObjectPoolConfig config) {

        Set<HostAndPort> hostAndPortSet = new HashSet<>();

        String[] redisAddressArray = redisClusterAddress.split(";");
        for (String redisAddress : redisAddressArray) {
            String[] hostAndPort = redisAddress.split(":");
            String host = hostAndPort[0];
            String port = hostAndPort[1];

            hostAndPortSet.add(new HostAndPort(host, Integer.valueOf(port)));
        }

        this.jedisCluster = new JedisCluster(hostAndPortSet, config);
    }

    /**
     * 生成唯一ID
     *
     * @return
     */
    public String generateId() {
        return UUID.randomUUID().toString();
    }

    /**
     * 校验幂等
     *
     * @param uniqueId
     * @return
     */
    public boolean checkIdempotence(String uniqueId) {
        return saveIfAbsent(uniqueId);
    }

    /**
     * 若uniqueId不存在, 则保存uniqueId值, 并返回1
     * 若uniqueId存在, 返回0
     *
     * @param uniqueId
     * @return
     */
    private boolean saveIfAbsent(String uniqueId) {
        return jedisCluster.setnx(uniqueId, "1") == 1;
    }

    public static void main(String[] args) {
        String redisClusterAddress = "127.0.0.1:6379";
        IdempotenceService idempotenceService = new IdempotenceService(redisClusterAddress, null);

        String outOrderId = idempotenceService.generateId();
        String id = pay(outOrderId);
        if (idempotenceService.checkIdempotence(id)) {
            System.out.println("幂等校验通过");
        } else {
            System.out.println("幂等校验不通过");
        }


        String outOrderId2 = idempotenceService.generateId();
        String id2 = pay(outOrderId2);
        if (idempotenceService.checkIdempotence(id2)) {
            System.out.println("幂等校验通过");
        } else {
            System.out.println("幂等校验不通过");
        }


    }

    private static String pay(String outOrderId) {
        System.out.println("模拟支付, 订单号: " + outOrderId);
        return outOrderId;
    }

}
