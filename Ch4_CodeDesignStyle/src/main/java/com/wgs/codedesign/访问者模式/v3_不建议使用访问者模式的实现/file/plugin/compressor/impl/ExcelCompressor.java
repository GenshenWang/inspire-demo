package com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.plugin.compressor.impl;


import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.file.ResourceFile;
import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.plugin.compressor.Compressor;

/**
 * @author: wanggenshen
 * @date: 2020/4/23 22:54.
 * @description: 负责文本压缩,将对文本的操作和文本本身解耦
 */
public class ExcelCompressor implements Compressor {

    @Override
    public void compress(ResourceFile resourceFile) {
        System.out.println("压缩Excel文件" + resourceFile);

    }
}
