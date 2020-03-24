package com.wgs.codedesign.工厂模式.v2.factory;

import com.wgs.codedesign.工厂模式.parser.IRuleConfigParser;
import com.wgs.codedesign.工厂模式.parser.YamlRuleConfigParser;

/**
 * @author: wanggenshen
 * @date: 2020/3/23 21:51.
 * @description: XXX
 */
public class YamlRuleConfigParserFactory implements RuleConfigParserFactory {

    @Override
    public IRuleConfigParser createParser() {
        return new YamlRuleConfigParser();
    }
}
