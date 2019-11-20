package com.wgs.inspire.chain.model;

import lombok.Data;

/**
 * Created by wanggenshen
 * Date: on 2019/11/20 22:59.
 * Description: 拦截器上下文类
 */
@Data
public class InspireContext {

    private InspireRequest request;
    private InspireResponse response;
}
