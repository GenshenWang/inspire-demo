package com.wgs.codedesign.工厂模式.v3.parser;

import org.dom4j.Document;
import org.dom4j.DocumentException;

import java.io.InputStream;

/**
 * @author: wanggenshen
 * @date: 2020/3/25 00:31.
 * @description: XXX
 */
public interface ResourceReader {

    Document read(InputStream inputStream) throws DocumentException;
}
