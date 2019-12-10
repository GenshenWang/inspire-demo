package com.wgs.auth.service;

import com.wgs.auth.constant.AuthConstants;
import com.wgs.auth.request.ApiRequest;
import com.wgs.auth.util.MD5Util;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanggenshen
 * Date: on 2019/12/5 23:43.
 * Description: Token类
 */
@Slf4j
@NoArgsConstructor
public class AuthToken {

    /**
     * 时间窗口为10min, 单位毫秒
     */
    private static final long TIME_WINDOW_MS = 10 * 60  * 1000;

    /**
     * token
     */
    private String token;

    /**
     * token生成时间
     */
    private long createTime;

    /**
     * token过期时间
     */
    private long expiredTimeWindow = TIME_WINDOW_MS;


    public AuthToken(String token, long createTime) {
        this.token = token;
        this.createTime = createTime;
    }

    public AuthToken(String token, long createTime, long expiredTimeWindow) {
        this.token = token;
        this.createTime = createTime;
        this.expiredTimeWindow = expiredTimeWindow;
    }

    public static AuthToken generateToken(String originalUrl, String password, String appKey, long timeStamp) {

        // 使用map存储, 加密的时候可以不区分参数顺序
        Map<String, String> paramsMap = new HashMap<>(8);
        paramsMap.put(AuthConstants.ORIGIN_URL, originalUrl);
        paramsMap.put(AuthConstants.APP_KEY, appKey);
        paramsMap.put(AuthConstants.TIME_STAMP, String.valueOf(timeStamp));
        paramsMap.put(AuthConstants.PASS_WORD, password);
        ApiRequest apiRequest = ApiRequest.buildApiRequest(paramsMap);

        try {
            String serverToken = MD5Util.encrypt(apiRequest.toString());
            return new AuthToken(serverToken, timeStamp);
        } catch (Exception e) {
            log.error("encrypt exception : " + e.toString());
            return null;
        }
    }

    public String getToken() {
        return this.token;
    }

    /**
     * 判断token是否过期
     *
     * @return
     */
    public boolean isExpired() {

        long currentTime = System.currentTimeMillis();
        // 超过窗口时间, 则视为过期
        if ((currentTime - this.createTime) > expiredTimeWindow) {
            return true;
        }
        return false;
    }

    /**
     * 判断客户端和服务端的token是否相等
     *
     * @param authToken
     * @return
     */
    public boolean isMatched(AuthToken authToken) {
        return this.token.equals(authToken.getToken());
    }

}
