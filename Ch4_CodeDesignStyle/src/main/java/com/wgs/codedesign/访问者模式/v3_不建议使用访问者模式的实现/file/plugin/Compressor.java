package com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.plugin;


import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.file.ExcelFileV3;
import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.file.PptFileV3;
import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.file.TxtFileV3;

/**
 * @author: wanggenshen
 * @date: 2020/4/23 22:54.
 * @description: 负责文本压缩,将对文本的操作和文本本身解耦
 */
public class Compressor {

    public void compress(ExcelFileV3 excelFile) {
        System.out.println("压缩Excel文件" + excelFile);

    }

    public void compress(PptFileV3 pptFile) {
        System.out.println("压缩PPT文件" + pptFile);

    }

    public void compress(TxtFileV3 txtFile) {
        System.out.println("压缩TXT文件" + txtFile);
    }
}
