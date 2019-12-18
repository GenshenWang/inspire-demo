package com.wgs.codedesign.ocp.v2.interceptor;

import com.wgs.codedesign.ocp.v2.ApiStatInfo;
import com.wgs.codedesign.ocp.v2.interceptor.annotation.InterceptorOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.List;

/**
 * Created by wanggenshen
 * Date: on 2019/12/11 19:45.
 * Description: 负责拦截器链的初始化和执行
 */
@Component
public class ApiInterceptorChainClient {

    @Autowired
    private List<ApiAlertInterceptor> apiAlertInterceptors;

    @PostConstruct
    public void loadInterceptors() {
        if (apiAlertInterceptors == null || apiAlertInterceptors.size() <= 0) {
            return;
        }
        apiAlertInterceptors.stream().forEach(interceptor -> resolveInterceptorOrder(interceptor));

        // 按优先级排序, order越小, 优先级越高
        Collections.sort(apiAlertInterceptors, (o1, o2) -> o1.getOrder() - o2.getOrder());

    }

    private void resolveInterceptorOrder(ApiAlertInterceptor interceptor) {
        if (interceptor.getClass().isAnnotationPresent(InterceptorOrder.class)) {
            int order = interceptor.getClass().getAnnotation(InterceptorOrder.class).order();
            interceptor.setOrder(order);
        }
    }

    public void processApiStatInfo(ApiStatInfo apiStatInfo) {
        apiAlertInterceptors.stream().forEach(apiAlertInterceptor -> apiAlertInterceptor.handler(apiStatInfo));
    }
}
