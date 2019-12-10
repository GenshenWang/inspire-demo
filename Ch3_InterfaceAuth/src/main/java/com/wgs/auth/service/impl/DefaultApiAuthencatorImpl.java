package com.wgs.auth.service.impl;

import com.wgs.auth.request.ApiRequest;
import com.wgs.auth.service.ApiAuthencator;
import com.wgs.auth.service.AuthToken;
import com.wgs.auth.service.CredentialStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by wanggenshen
 * Date: on 2019/12/7 20:20.
 * Description: 接口鉴权
 */
@Slf4j
@Component
public class DefaultApiAuthencatorImpl implements ApiAuthencator {

    @Resource(name = "defaultCredentialStorage")
    private CredentialStorage credentialStorage;

    @Override
    public void auth(String url) {
        ApiRequest apiRequest = ApiRequest.createFromFullUrl(url);
        auth(apiRequest);

    }

    @Override
    public void auth(ApiRequest apiRequest) {
        String appKey = apiRequest.getAppKey();
        String token = apiRequest.getToken();
        long timeStamp = apiRequest.getTimeStamp();
        String originalUrl = apiRequest.getOriginalUrl();

        AuthToken clientAuthToken = new AuthToken(token, timeStamp);
        if (clientAuthToken.isExpired()) {
            log.warn("Client[{}] token is expired", appKey);
            throw new RuntimeException("Client token is expired.");
        }

        String serverPassword = credentialStorage.getPasswordByAppkey(appKey);
        AuthToken serverAuthToken = AuthToken.generateToken(originalUrl, serverPassword, appKey, timeStamp);
        if (serverAuthToken == null || !serverAuthToken.isMatched(clientAuthToken)) {
            throw new RuntimeException("Token verification failed.");
        }

    }
}
