package com.wgs.codedesign.桥接模式.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: wanggenshen
 * @date: 2020/3/29 00:35.
 * @description: XXX
 */
/**
 * Created by wanggenshen
 * Date: on 2019/12/11 10:27.
 * Description: API统计信息
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiStatInfo {
    private String api;
    private long requestCount;
    private long errorCount;
    private long durationSeconds;

}
