package com.wgs.codedesign.职责链模式.sentinel_02.chain.processor;

import com.wgs.codedesign.职责链模式.sentinel_02.ApiStatInfo;

/**
 * @author: wanggenshen
 * @date: 2020/4/15 23:39.
 * @description: XXX
 */
public class DegradeProcessor implements ApiProcessor {
    @Override
    public boolean processApiStat(ApiStatInfo apiStatInfo) {
        if (apiStatInfo.getErrorCount() > 10) {
            return false;
        }
        return true;
    }
}
