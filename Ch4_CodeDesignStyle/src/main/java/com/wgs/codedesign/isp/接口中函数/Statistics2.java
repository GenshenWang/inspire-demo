package com.wgs.codedesign.isp.接口中函数;

import java.util.Collection;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/18 00:54.
 * @description: 针对Statistics中的函数count(),提供更细粒度的方法
 */
public class Statistics2 {
    private Long max;
    private Long min;
    private Long avg;
    private Long sum;
    private Long percentile99;
    private Long percentile999;


    public Long countMax(Collection dataSet) {
        // 省略一些逻辑
        return 0L;
    }

    public Long countMin(Collection dataSet) {
        // 省略一些逻辑
        return 0L;
    }

    // ... 省略其他
}
