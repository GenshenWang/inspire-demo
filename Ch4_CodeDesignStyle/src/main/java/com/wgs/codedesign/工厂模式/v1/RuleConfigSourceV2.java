package com.wgs.codedesign.工厂模式.v1;

import com.wgs.codedesign.工厂模式.parser.*;
import com.wgs.codedesign.工厂模式.common_config.InvalidRuleConfigException;
import com.wgs.codedesign.工厂模式.common_config.RuleConfig;

/**
 * @author: wanggenshen
 * @date: 2020/3/23 21:25.
 * @description: XXX
 */
public class RuleConfigSourceV2 {

    public RuleConfig load(String ruleConfigFilePath) {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);
        IRuleConfigParser parser = createParser(ruleConfigFileExtension);
        if (parser == null) {
            throw new InvalidRuleConfigException( "Rule config file format is not supported: " + ruleConfigFilePath);
        }

        String configText = "";
        //从ruleConfigFilePath文件中读取配置文本到configText中
        RuleConfig ruleConfig = parser.parse(configText);
        return ruleConfig;

    }

    private IRuleConfigParser createParser(String ruleConfigFileExtension) {
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


    private String getFileExtension(String filePath) {
        //...解析文件名获取扩展名，比如rule.json，返回json
        return filePath.substring(filePath.indexOf(".") + 1);
    }
}
