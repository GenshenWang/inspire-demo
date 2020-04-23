package com.wgs.codedesign.访问者模式.v2_访问者模式.plugin;


import com.wgs.codedesign.访问者模式.v2_访问者模式.file.ExcelFile;
import com.wgs.codedesign.访问者模式.v2_访问者模式.file.PptFile;
import com.wgs.codedesign.访问者模式.v2_访问者模式.file.TxtFile;

/**
 * @author: wanggenshen
 * @date: 2020/4/23 22:42.
 * @description: 负责文本抽取
 */
public class Extractor implements Visitor{

    @Override
    public void visit(ExcelFile excelFile) {
        System.out.println("解析EXCEL内容, excelFile=" + excelFile);

    }

    @Override
    public void visit(PptFile pptFile) {
        System.out.println("解析PPT内容, PptFile=" + pptFile);

    }

    @Override
    public void visit(TxtFile txtFile) {
        System.out.println("解析TXT内容, TxtFile=" + txtFile);

    }

    public void extract(TxtFile txtFile) {

    }

    public void extract(ExcelFile excelFile) {

    }

    public void extract(PptFile pptFile) {

    }
}
