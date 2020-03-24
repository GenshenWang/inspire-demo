package com.wgs.codedesign.工厂模式.parser;

import com.wgs.codedesign.工厂模式.common_config.RuleConfig;

/**
 * @author: wanggenshen
 * @date: 2020/3/23 21:30.
 * @description: XXX
 */
public class IRuleConfigParser {

    public RuleConfig parse(String configText) {
        return new RuleConfig();
    }
}
