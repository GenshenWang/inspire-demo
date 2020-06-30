package com.wgs.idempotent.v2.idgenerator;

import java.util.UUID;

/**
 * @author: wanggenshen
 * @date: 2020/6/30 22:02.
 * @description: XXX
 */
public class UuidIdempotenceIdGenerator implements IdempotenceIdGenerator {

    @Override
    public String generateIdempotenceId() {
        return UUID.randomUUID().toString();
    }
}
