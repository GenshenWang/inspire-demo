package com.wgs.demo.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by wanggenshen
 * Date: on 2019/11/22 23:44.
 * Description: XXX
 */
@Data
public class OrderDTO {

    private long orderId;
    private Date createTime;
}
