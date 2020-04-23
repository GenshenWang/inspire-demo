package com.wgs.codedesign.访问者模式.v1;

import com.google.common.collect.Lists;
import com.wgs.codedesign.访问者模式.v1.file.ResourceFile;
import com.wgs.codedesign.访问者模式.v1.file.ResourceFileFactory;

import java.util.List;

/**
 * @author: wanggenshen
 * @date: 2020/4/23 22:28.
 * @description: XXX
 */
public class Main1 {

    public static void main(String[] args) {

        Extractor extractor = new Extractor();
        Compressor compressor = new Compressor();
        List<ResourceFile> fileList = getAllFiles();
        for (ResourceFile file : fileList) {
            file.type(extractor);

            file.type(compressor);
        }
    }

    private static List<ResourceFile> getAllFiles() {
        List<ResourceFile> fileList = Lists.newArrayList();
        fileList.add(ResourceFileFactory.createResourceFile("a.ppt"));
        fileList.add(ResourceFileFactory.createResourceFile("b.txt"));
        fileList.add(ResourceFileFactory.createResourceFile("c.excel"));

        return fileList;
    }
}
