package com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.file;


import com.wgs.codedesign.访问者模式.v3_不建议使用访问者模式的实现.file.ResourceTypeEnum;

/**
 * @author: wanggenshen
 * @date: 2020/4/23 20:21.
 * @description: XXX
 */
public class TxtFileV3 extends ResourceFile {

    public TxtFileV3(String filePath) {
        super(filePath);
    }

    @Override
    public ResourceTypeEnum resourceType() {
        return ResourceTypeEnum.TXT;
    }
}
