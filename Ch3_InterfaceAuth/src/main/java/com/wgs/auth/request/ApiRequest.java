package com.wgs.auth.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanggenshen
 * Date: on 2019/12/7 19:47.
 * Description: Client请求, 包括HTTP请求和RPC请求
 *              采用OOP的编程风格
 *
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiRequest {

    /**
     * 正常的接口请求, 包括HTTP接口请求url 或 RPC接口请求
     */
    private String originalUrl;

    /**
     * 加密后的token
     */
    private String token;

    /**
     * 调用方身份标识
     */
    private String appKey;

    /**
     * 调用方调用时的时间戳
     */
    private long timeStamp;


    /**
     * 解析客户端传过来的带有token信息的url中的参数
     *
     * @param url
     * @return
     */
    public static ApiRequest createFromFullUrl(String url) {
        Map<String, String> paramsMap = resolveUrl(url);
        String originalUrl = url.split("\\?")[0];
        originalUrl = buildOriginalUrl(originalUrl, paramsMap);
        String token = "";
        String appKey = "";
        long timeStamp = 0;
        if (paramsMap != null && paramsMap.size() > 0) {
            token = paramsMap.get("token");
            appKey = paramsMap.get("appKey");
            timeStamp = Long.parseLong(paramsMap.get("timeStamp"));
        }
        ApiRequest apiRequest = new ApiRequest(originalUrl, token, appKey, timeStamp);
        return apiRequest;
    }

    private static String buildOriginalUrl(String originalUrl, Map<String, String> paramsMap) {

        StringBuilder sb = new StringBuilder(originalUrl).append("?");
        for (Map.Entry<String, String> entry : paramsMap.entrySet()){
            String key = entry.getKey();
            if (!"token".equals(key) && !"appKey".equals(key)
                    && !"timeStamp".equals(key) && !"password".equals(key)) {
                sb.append(key).append("=").append(entry.getValue());
            }
        }
        return sb.toString();
    }

    /**
     *  将appKey、timeStamp时间戳、token信息拼接在原始url后
     *  format: www.xxx.com/getUser?id=123&appKey=abc&token=1232jkahdka&timeStamp=12345555
     *
     * @param originalUrl   调用方的接口url
     * @param paramsMap     appKey、timeStamp、token等参数
     * @return
     */
    public static String concatUrl(String originalUrl, Map<String, String> paramsMap) {
        StringBuilder sb = new StringBuilder(originalUrl);
        for (Map.Entry<String, String> param : paramsMap.entrySet()) {
            sb.append("&").append(param.getKey()).append("=").append(param.getValue());
        }
        return sb.toString();
    }

    /**
     * 从拼接后的url中解析出参数信息
     *
     * @param url
     * @return
     */
    private static Map<String, String> resolveUrl(String url) {
        if (url == null || url.length() <= 0) {
            return null;
        }
        try {
            String subStr = url.substring(url.indexOf("?") + 1);
            String[] paramsKvArr = subStr.split("&");
            if (paramsKvArr == null || paramsKvArr.length < 1) {
                return null;
            }

            Map<String, String> paramsMap = new HashMap<>();
            for (String paramsKvStr : paramsKvArr) {
                paramsMap.put(getKey(paramsKvStr), getValue(paramsKvStr));
            }

            return paramsMap;

        } catch (Exception e) {
            throw new RuntimeException("resolve url error " + e.toString());
        }
    }

    private static String getKey(String paramsKvStr) {
        return paramsKvStr.substring(0, paramsKvStr.indexOf("="));
    }

    private static String getValue(String paramsKvStr) {
        return paramsKvStr.substring(paramsKvStr.indexOf("=") + 1);
    }



}
