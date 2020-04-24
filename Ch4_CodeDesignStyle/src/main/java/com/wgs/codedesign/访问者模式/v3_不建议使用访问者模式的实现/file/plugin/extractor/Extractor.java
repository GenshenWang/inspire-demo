package com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.plugin.extractor;

import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.file.ResourceFile;

/**
 * @author: wanggenshen
 * @date: 2020/4/24 23:02.
 * @description: XXX
 */
public interface Extractor {
    void extract(ResourceFile resourceFile);
}
