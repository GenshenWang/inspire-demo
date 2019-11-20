package com.wgs.inspire.chain.aop;

import com.wgs.inspire.chain.interceptor.InterceptorChainClient;
import com.wgs.inspire.chain.model.InspireContext;
import com.wgs.inspire.chain.model.InspireRequest;
import com.wgs.inspire.chain.model.InspireResponse;
import com.wgs.inspire.chain.rpc.RpcResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanggenshen
 * Date: on 2019/11/20 23:30.
 * Description: 拦截器AOP类
 */
@Aspect
@Component
public class MethodAspect {

    @Autowired
    private InterceptorChainClient interceptorChainClient;

    @Pointcut("execution(* com.wgs.inspire.chain.rpc.service.*.*(..))")
    private void doLogPointcut() {}


    @Around("doLogPointcut()")
    public Object doAfterReturning(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("====invoke aop start====");

        InspireContext inspireContext = buildInspireContext(joinPoint);

        // 目标方法之前
        interceptorChainClient.processBefore(inspireContext);

        // 目标方法执行之后
        RpcResult result = (RpcResult) joinPoint.proceed();
        inspireContext.getResponse().setData(result);
        interceptorChainClient.processAfter(inspireContext);

        System.out.println("====invoke aop end====");

        return result;
    }

    private InspireContext buildInspireContext(ProceedingJoinPoint joinPoint) {

        InspireRequest inspireRequest = new InspireRequest();
        // 方法名
        inspireRequest.setMethodName(joinPoint.getSignature().getName());

        // 参数名
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] paramsNames = methodSignature.getParameterNames();

        // 参数值
        Object[] paramsValue = joinPoint.getArgs();

        Map<String, Object> paramsMap = new HashMap<>();
        for (int i = 0; i < paramsNames.length; i++) {
            paramsMap.put(paramsNames[i], paramsValue[i]);
        }

        inspireRequest.setParamsMap(paramsMap);
        InspireContext inspireContext = new InspireContext();
        inspireContext.setRequest(inspireRequest);
        inspireContext.setResponse(new InspireResponse());

        return inspireContext;
    }
}
