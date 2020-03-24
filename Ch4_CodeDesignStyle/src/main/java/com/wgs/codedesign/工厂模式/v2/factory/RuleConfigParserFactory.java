package com.wgs.codedesign.工厂模式.v2.factory;

import com.wgs.codedesign.工厂模式.parser.IRuleConfigParser;

/**
 * @author: wanggenshen
 * @date: 2020/3/23 21:51.
 * @description: XXX
 */
public interface RuleConfigParserFactory {

    IRuleConfigParser createParser();

}
