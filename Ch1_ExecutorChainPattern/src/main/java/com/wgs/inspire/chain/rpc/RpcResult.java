package com.wgs.inspire.chain.rpc;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by wanggenshen
 * Date: on 2019/11/20 23:09.
 * Description: XXX
 */
@Data
public class RpcResult<T> implements Serializable{

    private Integer code;
    private boolean success;
    private String msg;
    private T data;
}
