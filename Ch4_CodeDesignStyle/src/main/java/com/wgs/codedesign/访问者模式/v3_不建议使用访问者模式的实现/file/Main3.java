package com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file;

import com.google.common.collect.Lists;
import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.file.ResourceFile;
import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.file.ResourceFileFactory;
import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.plugin.compressor.Compressor;
import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.plugin.compressor.CompressorFactory;
import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.plugin.extractor.Extractor;
import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.plugin.extractor.ExtractorFactory;

import java.util.List;

/**
 * @author: wanggenshen
 * @date: 2020/4/23 22:28.
 * @description: XXX
 */
public class Main3 {

    public static void main(String[] args) {


        List<ResourceFile> fileList = getAllFiles();
        for (ResourceFile file : fileList) {
            Compressor compressor = CompressorFactory.getCompressor(file.resourceType());
            compressor.compress(file);

            Extractor extractor = ExtractorFactory.getExtractor(file.resourceType());
            extractor.extract(file);
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
