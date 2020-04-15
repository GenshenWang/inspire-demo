package com.wgs.codedesign.职责链模式.sentinel_02.chain;

import com.wgs.codedesign.职责链模式.sentinel_02.chain.processor.DegradeProcessor;
import com.wgs.codedesign.职责链模式.sentinel_02.chain.processor.FlowProcessor;

/**
 * @author: wanggenshen
 * @date: 2020/4/15 23:43.
 * @description: XXX
 */
public class ApiProcessorChainBuilder {

    public ApiProcessorChain builder() {
        ApiProcessorChain processorChain = new ApiProcessorChain();
        processorChain.addProcessor(new FlowProcessor());
        processorChain.addProcessor(new DegradeProcessor());

        return processorChain;
    }

}
