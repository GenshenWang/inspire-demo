package com.wgs.codedesign.访问者模式.v2_访问者模式.plugin;

import com.wgs.codedesign.访问者模式.v2_访问者模式.file.ExcelFile;
import com.wgs.codedesign.访问者模式.v2_访问者模式.file.PptFile;
import com.wgs.codedesign.访问者模式.v2_访问者模式.file.TxtFile;

/**
 * @author: wanggenshen
 * @date: 2020/4/23 23:03.
 * @description: XXX
 */
public interface Visitor {
    void visit(ExcelFile excelFile);
    void visit(PptFile pptFile);
    void visit(TxtFile txtFile);

}
