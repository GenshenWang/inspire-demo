package com.wgs.inspire.chain.interceptor;

import com.wgs.inspire.chain.annotation.InterceptorOrder;
import com.wgs.inspire.chain.model.InspireContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by wanggenshen
 * Date: on 2019/11/20 23:06.
 * Description: 统计接口的调用时长
 */
@Slf4j
@Component
@InterceptorOrder(order = 20)
public class CostTimeInterceptor extends InspireInterceptor {

    private long start;
    private long end;

    @Override
    public boolean executeBefore(InspireContext context) {
        start = System.currentTimeMillis();
        return super.executeBefore(context);
    }

    @Override
    public void executeAfter(InspireContext context) {
        end = System.currentTimeMillis();
        long costTime = end - start;
        log.info("invoke method:[{}] costTime:{}", context.getRequest().getMethodName(), costTime);
    }

}
