package com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.file;

/**
 * @author: wanggenshen
 * @date: 2020/4/23 22:28.
 * @description: XXX
 */
public class ResourceFileFactory {

    public static ResourceFile createResourceFile(String filePath) {
        String suffix = filePath.substring(filePath.lastIndexOf(".") + 1);
        if ("txt".equals(suffix)) {
            return new TxtFileV3(filePath);
        } else if ("ppt".equals(suffix)) {
            return new PptFileV3(filePath);
        } if ("excel".equals(suffix)) {
            return new ExcelFileV3(filePath);
        }
        return null;
    }


}
