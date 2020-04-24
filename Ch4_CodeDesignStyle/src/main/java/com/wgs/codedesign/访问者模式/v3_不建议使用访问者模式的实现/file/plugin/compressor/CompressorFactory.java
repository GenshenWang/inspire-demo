package com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.plugin.compressor;

import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.ResourceTypeEnum;
import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.plugin.compressor.impl.ExcelCompressor;
import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.plugin.compressor.impl.PptCompressor;
import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.plugin.compressor.impl.TxtCompressor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wanggenshen
 * @date: 2020/4/24 23:00.
 * @description: XXX
 */
public class CompressorFactory {
    private static final Map<ResourceTypeEnum, Compressor> map = new HashMap<>();
    static {
        map.put(ResourceTypeEnum.EXCEL, new ExcelCompressor());
        map.put(ResourceTypeEnum.PPT, new PptCompressor());
        map.put(ResourceTypeEnum.TXT, new TxtCompressor());
    }

    public static Compressor getCompressor(ResourceTypeEnum resourceType) {
        return map.get(resourceType);
    }
}
