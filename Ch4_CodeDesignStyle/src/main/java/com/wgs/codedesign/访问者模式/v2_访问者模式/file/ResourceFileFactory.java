package com.wgs.codedesign.访问者模式.v2_访问者模式.file;

/**
 * @author: wanggenshen
 * @date: 2020/4/23 22:28.
 * @description: XXX
 */
public class ResourceFileFactory {

    public static ResourceFile createResourceFile(String filePath) {
        String suffix = filePath.substring(filePath.lastIndexOf(".") + 1);
        if ("txt".equals(suffix)) {
            return new TxtFile(filePath);
        } else if ("ppt".equals(suffix)) {
            return new PptFile(filePath);
        } if ("excel".equals(suffix)) {
            return new ExcelFile(filePath);
        }
        return null;
    }


}
