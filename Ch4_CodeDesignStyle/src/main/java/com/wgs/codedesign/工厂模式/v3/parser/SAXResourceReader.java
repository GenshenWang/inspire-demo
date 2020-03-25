package com.wgs.codedesign.工厂模式.v3.parser;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * @author: wanggenshen
 * @date: 2020/3/25 00:33.
 * @description: XXX
 */
public class SAXResourceReader implements ResourceReader {

    @Override
    public Document read(InputStream inputStream) throws DocumentException {
        SAXReader reader = new SAXReader();
        return reader.read(inputStream);
    }
}
