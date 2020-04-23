package com.wgs.codedesign.访问者模式.v1;

import com.wgs.codedesign.访问者模式.v1.file.ExcelFile;
import com.wgs.codedesign.访问者模式.v1.file.PptFile;
import com.wgs.codedesign.访问者模式.v1.file.TxtFile;

/**
 * @author: wanggenshen
 * @date: 2020/4/23 22:42.
 * @description: 负责文本抽取
 */
public class Extractor {

    public void extract(TxtFile txtFile) {
        System.out.println("解析TXT内容, TxtFile=" + txtFile);

    }

    public void extract(ExcelFile excelFile) {
        System.out.println("解析EXCEL内容, excelFile=" + excelFile);

    }

    public void extract(PptFile pptFile) {
        System.out.println("解析PPT内容, PptFile=" + pptFile);

    }
}
