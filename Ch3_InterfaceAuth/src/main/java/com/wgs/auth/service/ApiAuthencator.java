package com.wgs.auth.service;

import com.wgs.auth.request.ApiRequest;

/**
 * Created by wanggenshen
 * Date: on 2019/12/7 20:18.
 * Description: 触发鉴权逻辑的入口
 */
public interface ApiAuthencator {

    void auth(String url);

    void auth(ApiRequest apiRequest);
}
