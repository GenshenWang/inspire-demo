package com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.file;

/**
 * @author: wanggenshen
 * @date: 2020/4/23 20:11.
 * @description: 资源文件定义
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
