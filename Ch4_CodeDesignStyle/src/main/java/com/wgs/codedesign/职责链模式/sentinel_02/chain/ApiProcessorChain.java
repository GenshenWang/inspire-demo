package com.wgs.codedesign.职责链模式.sentinel_02.chain;

import com.wgs.codedesign.职责链模式.sentinel_02.ApiStatInfo;
import com.wgs.codedesign.职责链模式.sentinel_02.chain.processor.ApiProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wanggenshen
 * @date: 2020/4/15 23:41.
 * @description: XXX
 */
public class ApiProcessorChain {

    private List<ApiProcessor> apiProcessors = new ArrayList<>();

    public void addProcessor(ApiProcessor apiProcessor) {
        this.apiProcessors.add(apiProcessor);
    }

    public void process(ApiStatInfo apiStatInfo) {
        for (ApiProcessor apiProcessor : apiProcessors) {
            boolean result = apiProcessor.processApiStat(apiStatInfo);
            if (!result) {
                throw new RuntimeException("发生限流或降级");
            }
        }
    }
}
