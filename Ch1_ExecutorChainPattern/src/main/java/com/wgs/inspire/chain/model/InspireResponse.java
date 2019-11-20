package com.wgs.inspire.chain.model;

import lombok.Data;

import java.util.Map;

/**
 * Created by wanggenshen
 * Date: on 2019/11/20 22:59.
 * Description: 目标方法执行结果
 */
@Data
public class InspireResponse<T> {

    /**
     * 目标方法执行结果的数据
     */
    private T data;
}
