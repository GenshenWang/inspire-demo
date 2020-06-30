package com.wgs.idempotent.v2.storage;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: wanggenshen
 * @date: 2020/6/30 22:13.
 * @description: XXX
 */
public class RedisClusterIdempotenceStorage implements IdempotenceStorage {

    private JedisCluster jedisCluster;

    public RedisClusterIdempotenceStorage(String redisClusterAddress, GenericObjectPoolConfig config) {
        Set<HostAndPort> hostAndPortSet = parseHostAndPorts(redisClusterAddress);
        this.jedisCluster = new JedisCluster(hostAndPortSet, config);
    }

    private Set<HostAndPort> parseHostAndPorts(String redisClusterAddress) {
        Set<HostAndPort> hostAndPortSet = new HashSet<>();

        String[] redisAddressArray = redisClusterAddress.split(";");
        for (String redisAddress : redisAddressArray) {
            String[] hostAndPort = redisAddress.split(":");
            String host = hostAndPort[0];
            String port = hostAndPort[1];

            hostAndPortSet.add(new HostAndPort(host, Integer.valueOf(port)));
        }

        return hostAndPortSet;
    }

    @Override
    public boolean saveIfAbsent(String idempotenceId) {
        return false;
    }

    @Override
    public void delete(String idempotenceId) {
        jedisCluster.del(idempotenceId);
    }
}
