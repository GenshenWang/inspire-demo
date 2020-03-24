package com.wgs.codedesign.工厂模式.v2;


import com.wgs.codedesign.工厂模式.v2.factory.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wanggenshen
 * @date: 2020/3/23 21:58.
 * @description: XXX
 */
public class RuleConfigParserFactoryMap {

    private static final Map<String, RuleConfigParserFactory> ruleConfigParserFactoryMap = new HashMap<>(4);

    static {
        ruleConfigParserFactoryMap.put("json", new JsonRuleConfigParserFactory());
        ruleConfigParserFactoryMap.put("xml", new XmlRuleConfigParserFactory());
        ruleConfigParserFactoryMap.put("yaml", new YamlRuleConfigParserFactory());
        ruleConfigParserFactoryMap.put("properties", new PropertiesRuleConfigParserFactory());
    }

    public static  RuleConfigParserFactory getParserFactory(String ruleConfigFileExtension) {
        return ruleConfigParserFactoryMap.get(ruleConfigFileExtension);
    }
}
