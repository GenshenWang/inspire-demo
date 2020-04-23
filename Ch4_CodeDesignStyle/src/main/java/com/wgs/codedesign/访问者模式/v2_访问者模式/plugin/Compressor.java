package com.wgs.codedesign.访问者模式.v2_访问者模式.plugin;


import com.wgs.codedesign.访问者模式.v2_访问者模式.file.ExcelFile;
import com.wgs.codedesign.访问者模式.v2_访问者模式.file.PptFile;
import com.wgs.codedesign.访问者模式.v2_访问者模式.file.TxtFile;

/**
 * @author: wanggenshen
 * @date: 2020/4/23 22:54.
 * @description: 负责文本压缩
 */
public class Compressor implements Visitor{

    @Override
    public void visit(ExcelFile excelFile) {
        System.out.println("压缩Excel文件" + excelFile);

    }

    @Override
    public void visit(PptFile pptFile) {
        System.out.println("压缩PPT文件" + pptFile);
    }

    @Override
    public void visit(TxtFile txtFile) {
        System.out.println("压缩TXT文件" + txtFile);
    }

    /* public void compress(ExcelFileV3 excelFile) {
    }

    public void compress(PptFileV3 pptFile) {

    }

    public void compress(TxtFileV3 txtFile) {

    }*/
}
