package com.wgs.codedesign.ocp.v2.interceptor;

import com.wgs.codedesign.ocp.v2.AlertRule;
import com.wgs.codedesign.ocp.v2.ApiStatInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by wanggenshen
 * Date: on 2019/12/11 18:10.
 * Description: XXX
 */
public abstract class ApiAlertInterceptor {

    @Getter
    @Setter
    private int order;

    public AlertRule getRule(String api) {
        return AlertRule.getMatchedRule(api);
    }

    public abstract void handler(ApiStatInfo apiStatInfo);

}
