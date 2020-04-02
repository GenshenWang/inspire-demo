package com.wgs.codedesign.组合模式.fileTree_v2;


import java.io.File;

/**
 * @author: wanggenshen
 * @date: 2020/4/2 15:04.
 * @description: 文件模型
 */
public class FileNode extends FileSystemNodeV2 {
    public FileNode(String path) {
        super(path);
    }

    @Override
    public int countNumOfFiles() {
        File file = new File(path);
        if (file.exists()) {
            return 1;
        }
        return 0;
    }

    @Override
    public long countSizeOfFiles() {
        File file = new File(path);
        if (file.exists()) {
            return file.length();
        }
        return 0;
    }
}
