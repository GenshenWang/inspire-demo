package com.wgs.idempotent.v2;

/**
 * @author: wanggenshen
 * @date: 2020/6/30 21:55.
 * @description: 重构幂等框架V1
 */
public interface IdempotenceServiceV2 {

    /**
     * 检查幂等
     *
     * @param uniqueId
     * @return
     */
    boolean checkIdempotence(String uniqueId);
}
