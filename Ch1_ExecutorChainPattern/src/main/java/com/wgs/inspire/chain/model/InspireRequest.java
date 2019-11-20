package com.wgs.inspire.chain.model;

import lombok.Data;

import java.util.Map;

/**
 * Created by wanggenshen
 * Date: on 2019/11/20 22:59.
 * Description: 目标方法中的入参请求
 */
@Data
public class InspireRequest {

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 方法入参
     */
    private Map<String, Object> paramsMap;
}
