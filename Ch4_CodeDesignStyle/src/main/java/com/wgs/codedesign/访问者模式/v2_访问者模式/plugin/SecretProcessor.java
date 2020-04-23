package com.wgs.codedesign.访问者模式.v2_访问者模式.plugin;

import com.wgs.codedesign.访问者模式.v2_访问者模式.file.ExcelFile;
import com.wgs.codedesign.访问者模式.v2_访问者模式.file.PptFile;
import com.wgs.codedesign.访问者模式.v2_访问者模式.file.TxtFile;

/**
 * @author: wanggenshen
 * @date: 2020/4/23 23:53.
 * @description: 文本加密
 */
public class SecretProcessor implements Visitor{
    @Override
    public void visit(ExcelFile excelFile) {
        System.out.println("Excel文本加密");
    }

    @Override
    public void visit(PptFile pptFile) {
        System.out.println("PPT文本加密");

    }

    @Override
    public void visit(TxtFile txtFile) {
        System.out.println("TXT文本加密");

    }
}
