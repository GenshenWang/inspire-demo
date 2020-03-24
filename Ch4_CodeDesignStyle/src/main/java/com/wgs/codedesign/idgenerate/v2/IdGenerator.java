package com.wgs.codedesign.idgenerate.v2;

/**
 * @author: wanggenshen
 * @date: 2020/3/8 11:03.
 * @description: XXX
 */
public interface IdGenerator {

    /**
     * 生成随机ID
     *
     * @return
     */
    String generateId() throws IdGenerationFailureException;
}
