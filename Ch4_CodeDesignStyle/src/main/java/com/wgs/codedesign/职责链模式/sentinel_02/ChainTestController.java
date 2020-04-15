package com.wgs.codedesign.职责链模式.sentinel_02;

import com.wgs.codedesign.职责链模式.sentinel_02.chain.ApiProcessorChain;
import com.wgs.codedesign.职责链模式.sentinel_02.chain.ApiProcessorChainProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: wanggenshen
 * @date: 2020/4/15 23:44.
 * @description: XXX
 */
@Controller
@RequestMapping("/chain")
public class ChainTestController {

    @RequestMapping
    @ResponseBody
    public String test(@RequestParam("api") String api) {

        ApiStatInfo apiStatInfo = mockApiStatInfo(api);
        try {
            ApiProcessorChain chain = ApiProcessorChainProvider.newProcessorChain();
            chain.process(apiStatInfo);
            return api + " : success";
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    private ApiStatInfo mockApiStatInfo(String api) {
        ApiStatInfo apiStatInfo = new ApiStatInfo();
        apiStatInfo.setApi(api);
        apiStatInfo.setRequestCount(2000L);
        apiStatInfo.setQps(2000L);

        return apiStatInfo;
    }
}
