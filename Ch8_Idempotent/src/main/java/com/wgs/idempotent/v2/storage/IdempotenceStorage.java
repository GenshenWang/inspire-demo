package com.wgs.idempotent.v2.storage;

/**
 * @author: wanggenshen
 * @date: 2020/6/30 22:11.
 * @description: XXX
 */
public interface IdempotenceStorage {

    /**
     * idempotenceId不存在, 保存并返回true
     * idempotenceId 存在,  返回false
     *
     * @param idempotenceId 幂等号
     * @return
     */
    boolean saveIfAbsent(String idempotenceId);

    void delete(String idempotenceId);
}
