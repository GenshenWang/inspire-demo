package com.wgs.codedesign.建造者模式;

import org.apache.commons.lang.StringUtils;

/**
 * @author: wanggenshen
 * @date: 2020/3/26 00:41.
 * @description: XXX
 */
public class ResourcePoolConfig_Builder {
    private String name;
    private int maxTotal;
    private int maxIdle;
    private int minIdle;


    private ResourcePoolConfig_Builder(Builder builder) {
        this.name = builder.name;
        this.maxTotal = builder.maxTotal;
        this.maxIdle = builder.maxIdle;
        this.minIdle = builder.minIdle;
    }

    public static class Builder {
        private static final int DEFAULT_MAX_TOTAL = 8;
        private static final int DEFAULT_MAX_IDLE = 8;
        private static final int DEFAULT_MIN_IDLE = 0;

        private String name;
        private int maxTotal;
        private int maxIdle;
        private int minIdle;

        public ResourcePoolConfig_Builder build() {
            // 必要的参数校验放在构造器初始化中
            if (StringUtils.isBlank(name)) {
                throw new IllegalArgumentException("name should not empty");
            }
            if (maxIdle > maxTotal) {
                throw new IllegalArgumentException("maxIdle should not older than maxTotal");
            }

            return new ResourcePoolConfig_Builder(this);
        }

        public Builder name(String name) {
            this.name = name;
            if (StringUtils.isBlank(name)) {
                throw new IllegalArgumentException("name should not empty");
            }
            return this;
        }

        public Builder maxTotal(int maxTotal) {
            this.maxTotal = maxTotal;
            if (maxTotal <= 0) {
                throw new IllegalArgumentException("maxTotal should positive");
            }
            return this;
        }

        public Builder maxIdle(int maxIdle) {
            this.maxIdle = maxIdle;
            if (maxIdle <= 0) {
                throw new IllegalArgumentException("maxIdle should positive");
            }
            return this;
        }

        public Builder minIdle(int minIdle) {
            this.minIdle = minIdle;
            if (minIdle <= 0) {
                throw new IllegalArgumentException("minIdle should positive");
            }
            return this;
        }
    }

    @Override
    public String toString() {
        return "ResourcePoolConfig_Builder{" +
                "name='" + name + '\'' +
                ", maxTotal=" + maxTotal +
                ", maxIdle=" + maxIdle +
                ", minIdle=" + minIdle +
                '}';
    }
}
