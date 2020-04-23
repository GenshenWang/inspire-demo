package com.wgs.codedesign.访问者模式.v2_访问者模式.file;


import com.wgs.codedesign.访问者模式.v2_访问者模式.plugin.Compressor;
import com.wgs.codedesign.访问者模式.v2_访问者模式.plugin.Extractor;
import com.wgs.codedesign.访问者模式.v2_访问者模式.plugin.Visitor;

/**
 * @author: wanggenshen
 * @date: 2020/4/23 20:21.
 * @description: XXX
 */
public class TxtFile extends ResourceFile {

    public TxtFile(String filePath) {
        super(filePath);
    }

    @Override
    public void type(Visitor visitor) {
        visitor.visit(this);
    }
}
