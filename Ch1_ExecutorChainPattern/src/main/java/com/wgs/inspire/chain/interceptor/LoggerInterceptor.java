package com.wgs.inspire.chain.interceptor;

import com.wgs.inspire.chain.annotation.InterceptorOrder;
import com.wgs.inspire.chain.model.InspireContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by wanggenshen
 * Date: on 2019/11/20 23:03.
 * Description: 打印目标方法参数拦截器
 */
@Slf4j
@Component
@InterceptorOrder(order = 0)
public class LoggerInterceptor extends InspireInterceptor {

    @Override
    public boolean executeBefore(InspireContext context) {
        return super.executeBefore(context);
    }

    @Override
    public void executeAfter(InspireContext context) {
        String methodName = context.getRequest().getMethodName();
        Map<String, Object> paramsMap = context.getRequest().getParamsMap();

        log.info("invoke method:[{}], params:{}", methodName, paramsMap.toString());
    }
}
