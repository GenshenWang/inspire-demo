package com.wgs.inspire.chain.interceptor;

import com.wgs.inspire.chain.annotation.InterceptorOrder;
import com.wgs.inspire.chain.model.InspireContext;
import com.wgs.inspire.chain.rpc.RpcResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wanggenshen
 * Date: on 2019/11/20 23:06.
 * Description: 方法调用次数、调用成功次数、调用失败次数统计
 */
@Slf4j
@Component
@InterceptorOrder(order = 10)
public class MethodConutInterceptor extends InspireInterceptor {

    /**
     * 接口调用成功的次数
     */
    private static final Map<String, Integer> successCountMap = new ConcurrentHashMap<>();

    /**
     * 接口调用成功的次数
     */
    private static final Map<String, Integer> failCountMap = new ConcurrentHashMap<>();

    /**
     * 接口调用总次数
     */
    private static final Map<String, Integer> allCountMap = new ConcurrentHashMap<>();

    @Override
    public boolean executeBefore(InspireContext context) {
        return super.executeBefore(context);
    }

    @Override
    public void executeAfter(InspireContext context) {
        String methodName = context.getRequest().getMethodName();
        RpcResult rpcResult = (RpcResult) context.getResponse().getData();
        if (rpcResult != null && rpcResult.isSuccess()) {
            setMethodCount(methodName, successCountMap);
        } else {
            setMethodCount(methodName, failCountMap);
        }
        setMethodCount(methodName, allCountMap);

        log.info("invoke method:[{}], success times:{}, fail times:{}, all times:{}", methodName,
                getMethodCount(methodName, successCountMap), getMethodCount(methodName, failCountMap), getMethodCount(methodName, allCountMap));

    }


    private int getMethodCount(String key, final Map<String, Integer> map) {
        if (map == null || map.size() == 0) {
            return 0;
        }
        return map.get(key);
    }

    private void setMethodCount(String key, final Map<String, Integer> map) {
        if (map.get(key) == null) {
            map.put(key, 1);
        } else {
            map.put(key, map.get(key) + 1);
        }

    }
}
