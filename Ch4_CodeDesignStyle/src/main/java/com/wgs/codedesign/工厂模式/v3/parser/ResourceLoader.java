package com.wgs.codedesign.工厂模式.v3.parser;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author: wanggenshen
 * @date: 2020/3/24 17:58.
 * @description: XXX
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    InputStream getResource(String location) throws FileNotFoundException;
}
