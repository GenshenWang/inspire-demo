package com.wgs.sentinel.v2.rule;

/**
 * @author: wanggenshen
 * @date: 2020/6/22 23:43.
 * @description: Api限流规则
 */
public class ApiLimit {

    private static final int DEFAULT_UNIT_SECONDS = 1;


    private String api;
    private int limit;
    private int unit = DEFAULT_UNIT_SECONDS;

    public ApiLimit() {
    }


    public ApiLimit(String api, int limit) {
        this.api = api;
        this.limit = limit;
        this.unit = DEFAULT_UNIT_SECONDS;
    }

    public ApiLimit(String api, int limit, int unit) {
        this.api = api;
        this.limit = limit;
        this.unit = unit;
    }

    public String getApi() {
        return api;
    }

    public int getLimit() {
        return limit;
    }

    public int getUnit() {
        return unit;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "ApiLimit{" +
                "api='" + api + '\'' +
                ", limit=" + limit +
                ", unit=" + unit +
                '}';
    }
}
