package com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.file;

import com.wgs.codedesign.访问者模式.v2_访问者模式.plugin.Visitor;

/**
 * @author: wanggenshen
 * @date: 2020/4/23 20:11.
 * @description: XXX
 */
public abstract class ResourceFile {

    protected String filePath;

    public String getFilePath() {
        return filePath;
    }

    public ResourceFile(String filePath) {
        this.filePath = filePath;
    }

}
