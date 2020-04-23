package com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.plugin;

import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.file.ExcelFileV3;
import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.file.PptFileV3;
import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.file.TxtFileV3;

/**
 * @author: wanggenshen
 * @date: 2020/4/24 00:33.
 * @description: XXX
 */
public class CompressorContext {
    private Compressor compressor;

    public CompressorContext(Compressor compressor) {
        this.compressor = compressor;
    }

    public void doCompress(String filePath) {
        String suffix = filePath.substring(filePath.lastIndexOf(".") + 1);
        if ("txt".equals(suffix)) {
            compressor.compress(new TxtFileV3(filePath));
        } else if ("ppt".equals(suffix)) {
            compressor.compress(new PptFileV3(filePath));
        } if ("excel".equals(suffix)) {
            compressor.compress(new ExcelFileV3(filePath));
        }
    }
}
