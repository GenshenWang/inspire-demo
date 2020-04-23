package com.wgs.codedesign.访问者模式.v2_访问者模式.file;

import com.wgs.codedesign.访问者模式.v2_访问者模式.plugin.Compressor;
import com.wgs.codedesign.访问者模式.v2_访问者模式.plugin.Extractor;
import com.wgs.codedesign.访问者模式.v2_访问者模式.plugin.Visitor;

/**
 * @author: wanggenshen
 * @date: 2020/4/23 20:11.
 * @description: XXX
 */
public abstract class ResourceFile {

    protected String filePath;

    public ResourceFile(String filePath) {
        this.filePath = filePath;
    }

    public abstract void type(Visitor visitor);

}
