package com.wgs.sentinel.v2.parser;


import com.wgs.sentinel.v2.rule.RuleConfig;

import java.io.InputStream;

/**
 * @author: wanggenshen
 * @date: 2020/6/24 00:16.
 * @description: 资源解析, 支持Yaml、Xml、Properties文件解析
 */
public interface RuleConfigParser {

    RuleConfig parse(InputStream in);
}
