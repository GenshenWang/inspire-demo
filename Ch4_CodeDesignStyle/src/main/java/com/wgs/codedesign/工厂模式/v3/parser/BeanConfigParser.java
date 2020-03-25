package com.wgs.codedesign.工厂模式.v3.parser;

import com.wgs.codedesign.工厂模式.v3.bean.BeanDefinition;

import java.io.InputStream;
import java.util.List;

/**
 * @author: wanggenshen
 * @date: 2020/3/24 17:47.
 * @description: XXX
 */
public interface BeanConfigParser {

    List<BeanDefinition> parse(InputStream inputStream);
}
