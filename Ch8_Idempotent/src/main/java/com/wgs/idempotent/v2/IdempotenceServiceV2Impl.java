package com.wgs.idempotent.v2;

import com.wgs.idempotent.v2.storage.IdempotenceStorage;

/**
 * @author: wanggenshen
 * @date: 2020/6/30 22:11.
 * @description: 幂等框架实现
 */
public class IdempotenceServiceV2Impl implements IdempotenceServiceV2 {

    private IdempotenceStorage idempotenceStorage;

    public IdempotenceServiceV2Impl(IdempotenceStorage idempotenceStorage) {
        this.idempotenceStorage = idempotenceStorage;
    }

    @Override
    public boolean checkIdempotence(String uniqueId) {
        return idempotenceStorage.saveIfAbsent(uniqueId);
    }
}
