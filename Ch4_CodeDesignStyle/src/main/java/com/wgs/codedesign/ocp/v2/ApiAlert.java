package com.wgs.codedesign.ocp.v2;

import com.wgs.codedesign.ocp.v2.interceptor.ApiInterceptorChainClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wanggenshen
 * Date: on 2019/12/11 09:55.
 * Description: API 接口监控
 */
@Component
public class ApiAlert {

    @Autowired
    private ApiInterceptorChainClient interceptorChainClient;

    /**
     * 是否需要发送告警
     */
    public void check(ApiStatInfo apiStatInfo) {
        interceptorChainClient.processApiStatInfo(apiStatInfo);
    }


}
