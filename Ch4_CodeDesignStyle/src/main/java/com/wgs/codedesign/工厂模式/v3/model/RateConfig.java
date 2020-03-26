package com.wgs.codedesign.工厂模式.v3.model;

/**
 * @author: wanggenshen
 * @date: 2020/3/24 16:57.
 * @description: XXX
 */
public class RateConfig {
    private String ip;
    private Integer port;

    public MongoConfig getMongoConfig() {
        return mongoConfig;
    }

    public void setMongoConfig(MongoConfig mongoConfig) {
        this.mongoConfig = mongoConfig;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    private MongoConfig mongoConfig;

    public RateConfig() {
    }

    public RateConfig(String ip, Integer port, MongoConfig mongoConfig) {
        this.ip = ip;
        this.port = port;
        this.mongoConfig = mongoConfig;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RateConfig that = (RateConfig) o;

        if (!ip.equals(that.ip)) return false;
        if (!port.equals(that.port)) return false;
        return mongoConfig.equals(that.mongoConfig);

    }

    @Override
    public int hashCode() {
        int result = ip.hashCode();
        result = 31 * result + port.hashCode();
        result = 31 * result + mongoConfig.hashCode();
        return result;
    }
}
