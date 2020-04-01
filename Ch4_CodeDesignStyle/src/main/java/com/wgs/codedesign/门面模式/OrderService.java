package com.wgs.codedesign.门面模式;

/**
 * @author: wanggenshen
 * @date: 2020/4/1 20:05.
 * @description: XXX
 */
public class OrderService {

    public String queryOrderBasicInfo(String orderId) {
        return "query orderBasicInfo:" + orderId;
    }

    public String queryAddressInfo(String orderId) {
        return "query addressInfo:" + orderId;
    }

    public String queryRefundInfo(String orderId) {
        return "query refundInfo:" + orderId;
    }


    /**
     * 使用门面形式, 包装以上接口
     *
     * @param orderId
     * @return
     */
    public String queryOrderInfo(String orderId) {
        String result = "";
        result += queryOrderBasicInfo(orderId);
        result += queryAddressInfo(orderId);
        result += queryRefundInfo(orderId);

        return result;
    }
}
