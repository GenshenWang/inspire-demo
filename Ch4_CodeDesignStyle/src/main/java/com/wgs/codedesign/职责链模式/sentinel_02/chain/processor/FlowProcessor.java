package com.wgs.codedesign.职责链模式.sentinel_02.chain.processor;

import com.wgs.codedesign.职责链模式.sentinel_02.ApiStatInfo;

/**
 * @author: wanggenshen
 * @date: 2020/4/15 23:37.
 * @description: XXX
 */
public class FlowProcessor implements ApiProcessor{


    @Override
    public boolean processApiStat(ApiStatInfo apiStatInfo) {
        if (apiStatInfo.getQps() > 1000) {
            return false;
        }

        return true;
    }
}
