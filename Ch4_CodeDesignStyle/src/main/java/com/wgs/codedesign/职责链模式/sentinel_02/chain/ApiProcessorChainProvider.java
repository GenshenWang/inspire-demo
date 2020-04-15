package com.wgs.codedesign.职责链模式.sentinel_02.chain;

/**
 * @author: wanggenshen
 * @date: 2020/4/15 23:54.
 * @description: XXX
 */
public class ApiProcessorChainProvider {

    private static volatile ApiProcessorChainBuilder builder = new ApiProcessorChainBuilder();


    public static ApiProcessorChain newProcessorChain() {
        return builder.builder();
    }
}
