package com.wgs.codedesign.组合模式.fileTree_v2;

/**
 * @author: wanggenshen
 * @date: 2020/4/2 14:56.
 * @description: XXX
 */
public abstract class FileSystemNodeV2 {
    protected String path;

    public FileSystemNodeV2(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public abstract int countNumOfFiles();

    public abstract long countSizeOfFiles();

}