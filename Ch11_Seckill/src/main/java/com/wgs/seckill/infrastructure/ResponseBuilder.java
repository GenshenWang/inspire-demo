package com.wgs.seckill.infrastructure;

public class ResponseBuilder {
    private ResponseBuilder() {
        throw new IllegalStateException("No instance");
    }

    public static <T> Response<T> ok() {
        return ok(null);
    }

    public static <T> Response<T> ok(T data) {
        Response<T> response = new Response<>();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> error(Integer code, String msg) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setMsg(msg);
        response.setCode(code);
        return response;
    }
}
