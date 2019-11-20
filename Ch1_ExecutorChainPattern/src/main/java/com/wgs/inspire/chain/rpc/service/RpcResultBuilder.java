package com.wgs.inspire.chain.rpc.service;

import com.wgs.inspire.chain.rpc.RpcResult;

/**
 * Created by wanggenshen
 * Date: on 2019/11/20 23:43.
 * Description: XXX
 */
public class RpcResultBuilder {

    public static <T> RpcResult<T> buildSuccess(T data) {
        RpcResult<T> result = new RpcResult();
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("success");
        result.setData(data);

        return result;
    }

    public static <T> RpcResult<T> buildFail(T data) {
        RpcResult<T> result = new RpcResult();
        result.setSuccess(false);
        result.setCode(500);
        result.setMsg("fail");
        result.setData(data);

        return result;
    }
}
