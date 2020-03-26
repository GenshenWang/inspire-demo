package com.wgs.codedesign.工厂模式.v3.model;

/**
 * @author: wanggenshen
 * @date: 2020/3/24 16:57.
 * @description: XXX
 */
public class MongoConfig {
    private String ip;
    private Integer port;


    public MongoConfig() {
    }

    public MongoConfig(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
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

        MongoConfig that = (MongoConfig) o;

        if (port != that.port) return false;
        return ip != null ? ip.equals(that.ip) : that.ip == null;

    }

    @Override
    public int hashCode() {
        int result = ip != null ? ip.hashCode() : 0;
        result = 31 * result + port;
        return result;
    }

    @Override
    public String toString() {
        return "RateConfig{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}
