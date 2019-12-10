package com.wgs.auth.request;

import com.wgs.auth.constant.AuthConstants;
import lombok.*;

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
@ToString
@EqualsAndHashCode
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


    public ApiRequest(String originalUrl, String token, String appKey, long timeStamp) {
        this.originalUrl = originalUrl;
        this.token = token;
        this.appKey = appKey;
        this.timeStamp = timeStamp;
    }

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
            token = paramsMap.get(AuthConstants.TOKEN);
            appKey = paramsMap.get(AuthConstants.APP_KEY);
            timeStamp = Long.parseLong(paramsMap.get(AuthConstants.TIME_STAMP));
        }
        ApiRequest apiRequest = new ApiRequest(originalUrl, token, appKey, timeStamp);
        return apiRequest;
    }

    private static String buildOriginalUrl(String originalUrl, Map<String, String> paramsMap) {

        StringBuilder sb = new StringBuilder(originalUrl).append("?");
        for (Map.Entry<String, String> entry : paramsMap.entrySet()){
            String key = entry.getKey();
            if (!AuthConstants.TOKEN.equals(key) && !AuthConstants.APP_KEY.equals(key)
                    && !AuthConstants.TIME_STAMP.equals(key) && !AuthConstants.PASS_WORD.equals(key)) {
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


    public static ApiRequest buildApiRequest(Map<String, String> paramsMap) {
        if (paramsMap == null || paramsMap.size() < 1) {
            return null;
        }
        String originalUrl = paramsMap.containsKey(AuthConstants.ORIGIN_URL) ? paramsMap.get(AuthConstants.ORIGIN_URL) : null;
        String token = paramsMap.containsKey(AuthConstants.TOKEN) ? paramsMap.get(AuthConstants.TOKEN) : null;
        String appKey = paramsMap.containsKey(AuthConstants.APP_KEY) ? paramsMap.get(AuthConstants.APP_KEY) : null;
        long timeStamp = paramsMap.containsKey(AuthConstants.TIME_STAMP) ? Long.parseLong(paramsMap.get(AuthConstants.TIME_STAMP)) : null;

        return new ApiRequest(originalUrl, token, appKey, timeStamp);


    }

}
