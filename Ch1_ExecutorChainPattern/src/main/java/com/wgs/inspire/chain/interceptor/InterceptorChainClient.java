package com.wgs.inspire.chain.interceptor;

import com.wgs.inspire.chain.annotation.InterceptorOrder;
import com.wgs.inspire.chain.model.InspireContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

/**
 * Created by wanggenshen
 * Date: on 2019/11/20 23:19.
 * Description: 拦截器链
 */
@Component
public class InterceptorChainClient {

    @Autowired
    private List<InspireInterceptor> interceptorList;


    @PostConstruct
    public void loadInterceptors() {
        if (!CollectionUtils.isEmpty(interceptorList)) {
            for (InspireInterceptor interceptor : interceptorList) {
                interceptor.setOrder(resolveOrder(interceptor));
            }

            Collections.sort(interceptorList, (o1, o2) -> o1.getOrder() - o2.getOrder());
        }
    }

    /**
     * 获取拦截器优先级
     *
     * @param interceptor
     * @return
     */
    private int resolveOrder(InspireInterceptor interceptor) {
        if (!interceptor.getClass().isAnnotationPresent(InterceptorOrder.class)) {
            return InterceptorOrder.LOWEST_ORDER;
        } else {
            return interceptor.getClass().getAnnotation(InterceptorOrder.class).order();
        }
    }

    public boolean processBefore(InspireContext context) {
        for (InspireInterceptor interceptor : interceptorList) {
            boolean isPass = interceptor.executeBefore(context);
            if (!isPass) {
                return false;
            }
        }
        return true;
    }

    public void processAfter(InspireContext context) {
        for (InspireInterceptor interceptor : interceptorList) {
            interceptor.executeAfter(context);
        }
    }

}
