package com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.plugin.extractor.impl;

import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.file.ResourceFile;
import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.plugin.extractor.Extractor;

/**
 * @author: wanggenshen
 * @date: 2020/4/24 23:10.
 * @description: XXX
 */
public class TxtExtractor implements Extractor {
    @Override
    public void extract(ResourceFile resourceFile) {
        System.out.println("获取Txt内容" + resourceFile.getFilePath());
    }
}
