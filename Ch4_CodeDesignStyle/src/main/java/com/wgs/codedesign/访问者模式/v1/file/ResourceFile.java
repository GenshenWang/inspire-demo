package com.wgs.codedesign.访问者模式.v1.file;

import com.wgs.codedesign.访问者模式.v1.Compressor;
import com.wgs.codedesign.访问者模式.v1.Extractor;

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

    public abstract void type(Extractor extractor);

    public abstract void type(Compressor compressor);


}
