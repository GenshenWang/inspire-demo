package com.wgs.codedesign.工厂模式.v1;

import com.wgs.codedesign.工厂模式.parser.*;

/**
 * @author: wanggenshen
 * @date: 2020/3/23 21:38.
 * @description: XXX
 */
public class RuleConfigParserFactory {

    public static IRuleConfigParser createParser(String ruleConfigFileExtension) {
        IRuleConfigParser parser = null;
        if ("json".equalsIgnoreCase(ruleConfigFileExtension)) {
            parser = new JsonRuleConfigParser();
        } else if ("xml".equalsIgnoreCase(ruleConfigFileExtension)) {
            parser = new XmlRuleConfigParser();
        } else if ("yaml".equalsIgnoreCase(ruleConfigFileExtension)) {
            parser = new YamlRuleConfigParser();
        } else if ("properties".equalsIgnoreCase(ruleConfigFileExtension)) {
            parser = new PropertiesRuleConfigParser();
        }
        return parser;
    }
}
