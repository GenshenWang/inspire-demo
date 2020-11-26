package com.wgs.seckill.infrastructure;

import lombok.Data;

@Data
public class Response<T> {
    private Integer code;
    private String msg;
    private Boolean success;
    private T data;
}
