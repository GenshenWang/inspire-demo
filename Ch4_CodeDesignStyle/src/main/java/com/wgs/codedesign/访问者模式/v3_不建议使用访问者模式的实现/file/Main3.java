package com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file;

import com.google.common.collect.Lists;
import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.file.ResourceFile;
import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.file.ResourceFileFactory;
import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.plugin.Compressor;
import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.plugin.CompressorContext;

import java.util.List;

/**
 * @author: wanggenshen
 * @date: 2020/4/23 22:28.
 * @description: XXX
 */
public class Main3 {

    public static void main(String[] args) {

        Compressor compressor = new Compressor();
        CompressorContext compressorContext = new CompressorContext(compressor);

        List<ResourceFile> fileList = getAllFiles();
        for (ResourceFile file : fileList) {
            compressorContext.doCompress(file.getFilePath());
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
