package com.wgs.sentinel.v2.parser;

import com.wgs.sentinel.v2.rule.RuleConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

/**
 * @author: wanggenshen
 * @date: 2020/6/24 00:16.
 * @description: XXX
 */
public class YamlRuleConfigParser implements RuleConfigParser {

    @Override
    public RuleConfig parse(InputStream in) {
        if (in != null) {
            Yaml yaml = new Yaml();
            return yaml.loadAs(in, RuleConfig.class);
        }

        return  null;
    }
}
