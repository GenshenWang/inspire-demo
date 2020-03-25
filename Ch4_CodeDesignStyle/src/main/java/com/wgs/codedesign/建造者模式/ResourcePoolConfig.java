package com.wgs.codedesign.建造者模式;

import org.apache.commons.lang.StringUtils;

/**
 * @author: wanggenshen
 * @date: 2020/3/26 00:36.
 * @description: XXX
 */
public class ResourcePoolConfig {
    private static final int DEFAULT_MAX_TOTAL = 8;
    private static final int DEFAULT_MAX_IDLE = 8;
    private static final int DEFAULT_MIN_IDLE = 0;

    private String name;
    private int maxTotal = DEFAULT_MAX_TOTAL;
    private int maxIdle = DEFAULT_MAX_IDLE;
    private int minIdle = DEFAULT_MIN_IDLE;


    public ResourcePoolConfig(String name, Integer maxTotal, Integer maxIdle, Integer minIdle) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("name should not be empty.");
        }
        this.name = name;

        if (maxTotal != null) {
            if (maxTotal <= 0) {
                throw new IllegalArgumentException("maxTotal should be positive.");
            }
            this.maxTotal = maxTotal;
        }
        
        if (maxIdle != null) {
            if (maxIdle <= 0) {
                throw new IllegalArgumentException("maxIdle shoule be positive");
            }
            this.maxIdle = maxIdle;
        }
        
        if (minIdle != null) {
            if (minIdle < 0) {
                throw new IllegalArgumentException("minIdle should be positive");
            }
            this.minIdle = minIdle;
        }
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public String getName() {
        return name;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }
}
