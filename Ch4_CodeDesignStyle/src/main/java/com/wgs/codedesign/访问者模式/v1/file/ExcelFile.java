package com.wgs.codedesign.访问者模式.v1.file;

import com.wgs.codedesign.访问者模式.v1.Compressor;
import com.wgs.codedesign.访问者模式.v1.Extractor;

/**
 * @author: wanggenshen
 * @date: 2020/4/23 20:21.
 * @description: XXX
 */
public class ExcelFile extends ResourceFile {

    public ExcelFile(String filePath) {
        super(filePath);
    }

    @Override
    public void type(Extractor extractor) {
        extractor.extract(this);
    }

    @Override
    public void type(Compressor compressor) {
        compressor.compress(this);
    }

}
