package com.wgs.auth.test;

import com.wgs.auth.constant.AuthConstants;
import com.wgs.auth.request.ApiRequest;
import com.wgs.auth.service.ApiAuthencator;
import com.wgs.auth.util.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanggenshen
 * Date: on 2019/12/8 10:19.
 * Description: 测试类, 模拟Client调用请求
 */
@Controller
@RequestMapping("/client")
public class InvokerController {

    @Resource
    private ApiAuthencator apiAuthencator;

    @RequestMapping("/invoke")
    @ResponseBody
    public void invoke() throws Exception {

        String originUrl = "https://www.abc.com/user?id=123";

        // client invoke method
        // mock params
        String currentTime = String.valueOf(System.currentTimeMillis());
        Map<String, String> encryptParamsMap = new HashMap<>();
        encryptParamsMap.put(AuthConstants.ORIGIN_URL, originUrl);
        encryptParamsMap.put(AuthConstants.APP_KEY, "order");
        encryptParamsMap.put(AuthConstants.TIME_STAMP, currentTime);
        encryptParamsMap.put(AuthConstants.PASS_WORD, "order123");
        ApiRequest apiRequest = ApiRequest.buildApiRequest(encryptParamsMap);

        // 使用ApiRequest对象 作为加密参数是为了保证不因参数位置变动导致加密结果不一致
        String token = MD5Util.encrypt(apiRequest.toString());

        // concat token/appKey/timeStamp after origin url, to rebuild a new url
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put(AuthConstants.TOKEN, token);
        paramsMap.put(AuthConstants.APP_KEY, "order");
        paramsMap.put(AuthConstants.TIME_STAMP, currentTime);
        String rebuildUrl = ApiRequest.concatUrl(originUrl, paramsMap);

        // mock network cost time
        Thread.sleep(1000);

        // server auth
        apiAuthencator.auth(rebuildUrl);
    }



}
