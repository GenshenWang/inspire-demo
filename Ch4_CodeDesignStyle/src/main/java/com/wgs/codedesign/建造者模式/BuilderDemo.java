package com.wgs.codedesign.建造者模式;

/**
 * @author: wanggenshen
 * @date: 2020/3/26 00:36.
 * @description: XXX
 */
public class BuilderDemo {

    public static void main(String[] args) {
        ResourcePoolConfig_Builder resourcePoolConfig = new ResourcePoolConfig_Builder.Builder()
                .name("wgs")
                .maxIdle(12)
                .minIdle(11)
                .maxTotal(20)
                .build();

        System.out.println(resourcePoolConfig);

        ResourcePoolConfig_Builder resourcePoolConfig2 = new ResourcePoolConfig_Builder.Builder()
                .name("")
                .maxIdle(12)
                .minIdle(22)
                .maxTotal(20)
                .build();

        System.out.println(resourcePoolConfig);
    }
}
