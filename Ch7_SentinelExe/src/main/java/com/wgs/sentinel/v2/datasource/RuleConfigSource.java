package com.wgs.sentinel.v2.datasource;

import com.wgs.sentinel.v2.rule.RuleConfig;

/**
 * @author: wanggenshen
 * @date: 2020/6/24 00:30.
 * @description: 配置资源, 配置来源可以是本地文件、数据库、分布式配置
 */
public interface RuleConfigSource {

    RuleConfig load();
}
