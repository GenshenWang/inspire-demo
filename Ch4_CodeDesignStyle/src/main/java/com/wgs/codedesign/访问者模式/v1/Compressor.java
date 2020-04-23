package com.wgs.codedesign.访问者模式.v1;

import com.wgs.codedesign.访问者模式.v1.file.ExcelFile;
import com.wgs.codedesign.访问者模式.v1.file.PptFile;
import com.wgs.codedesign.访问者模式.v1.file.TxtFile;

/**
 * @author: wanggenshen
 * @date: 2020/4/23 22:54.
 * @description: 负责文本压缩
 */
public class Compressor {

    public void compress(ExcelFile excelFile) {
        System.out.println("压缩Excel文件" + excelFile);
    }

    public void compress(PptFile pptFile) {
        System.out.println("压缩PPT文件" + pptFile);
    }

    public void compress(TxtFile txtFile) {
        System.out.println("压缩TXT文件" + txtFile);
    }
}
