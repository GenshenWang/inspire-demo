package com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.plugin.compressor;


import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.file.ResourceFile;

/**
 * @author: wanggenshen
 * @date: 2020/4/23 22:54.
 * @description: 负责文本压缩,将对文本的操作和文本本身解耦
 */
public interface Compressor {

    void compress(ResourceFile resourceFile);
}
