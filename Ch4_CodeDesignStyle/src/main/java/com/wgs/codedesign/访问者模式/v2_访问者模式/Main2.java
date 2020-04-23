package com.wgs.codedesign.访问者模式.v2_访问者模式;

import com.google.common.collect.Lists;
import com.wgs.codedesign.访问者模式.v2_访问者模式.file.ResourceFile;
import com.wgs.codedesign.访问者模式.v2_访问者模式.file.ResourceFileFactory;
import com.wgs.codedesign.访问者模式.v2_访问者模式.plugin.Compressor;
import com.wgs.codedesign.访问者模式.v2_访问者模式.plugin.Extractor;
import com.wgs.codedesign.访问者模式.v2_访问者模式.plugin.SecretProcessor;
import com.wgs.codedesign.访问者模式.v2_访问者模式.plugin.Visitor;

import java.util.List;

/**
 * @author: wanggenshen
 * @date: 2020/4/23 22:28.
 * @description: XXX
 */
public class Main2 {

    public static void main(String[] args) {

        Visitor extractor = new Extractor();
        Visitor compressor = new Compressor();
        Visitor secretProcessor = new SecretProcessor();


        List<ResourceFile> fileList = getAllFiles();
        for (ResourceFile file : fileList) {
            file.type(extractor);
            file.type(compressor);
            file.type(secretProcessor);
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
