package com.wgs.codedesign.isp.接口中函数;

import lombok.Data;
import lombok.Getter;

import java.util.Collection;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/18 00:53.
 * @description: XXX
 */
@Data
public class Statistics {
    private Long max;
    private Long min;
    private Long avg;
    private Long sum;
    private Long percentile99;
    private Long percentile999;

    public Statistics count(Collection dataSet) {
        Statistics statistics = new Statistics();
        //...省略计算逻辑...
        return statistics;
    }
}
