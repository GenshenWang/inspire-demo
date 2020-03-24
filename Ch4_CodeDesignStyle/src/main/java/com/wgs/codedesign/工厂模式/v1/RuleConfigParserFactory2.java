package com.wgs.codedesign.工厂模式.v1;

import com.wgs.codedesign.工厂模式.parser.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wanggenshen
 * @date: 2020/3/23 21:38.
 * @description: XXX
 */
public class RuleConfigParserFactory2 {

    private static final Map<String, IRuleConfigParser> configParserMap = new HashMap<>(4);

    static {
        configParserMap.put("json", new JsonRuleConfigParser());
        configParserMap.put("xml", new XmlRuleConfigParser());
        configParserMap.put("yaml", new YamlRuleConfigParser());
        configParserMap.put("properties", new PropertiesRuleConfigParser());
    }

    public static IRuleConfigParser createParser(String ruleConfigFileExtension) {
        if (ruleConfigFileExtension == null || ruleConfigFileExtension.isEmpty()) {
            throw new IllegalArgumentException("Invalid rule config file extension");
        }

        return configParserMap.get(ruleConfigFileExtension);
    }
}
