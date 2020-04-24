package com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.plugin.extractor;

import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.ResourceTypeEnum;
import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.plugin.extractor.impl.ExcelExtractor;
import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.plugin.extractor.impl.PptExtractor;
import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.plugin.extractor.impl.TxtExtractor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wanggenshen
 * @date: 2020/4/24 23:12.
 * @description: XXX
 */
public class ExtractorFactory {
    private static final Map<ResourceTypeEnum, Extractor> map = new HashMap<>();
    static {
        map.put(ResourceTypeEnum.EXCEL, new ExcelExtractor());
        map.put(ResourceTypeEnum.TXT, new TxtExtractor());
        map.put(ResourceTypeEnum.PPT, new PptExtractor());
    }

    public static Extractor getExtractor(ResourceTypeEnum resourceType) {
        return map.get(resourceType);
    }
}
