package com.wgs.auth.test;

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
@RequestMapping("/user")
public class InvokerController {

    @Resource
    private ApiAuthencator apiAuthencator;

    @RequestMapping("/getUser")
    @ResponseBody
    public void invoke() throws Exception {

        String originUrl = "https://www.abc.com/user?id=123";

        // client invoke method
        // mock params
        Map<String, String> encryptParamsMap = new HashMap<>();
        encryptParamsMap.put("originUrl", originUrl);
        encryptParamsMap.put("appKey", "order");
        encryptParamsMap.put("timeStamp", "15678901234");
        encryptParamsMap.put("password", "order123");
        String token = MD5Util.encrypt(encryptParamsMap.toString());

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("token", token);
        paramsMap.put("appKey", "order");
        paramsMap.put("timeStamp", "15678901234");
        String url = ApiRequest.concatUrl(originUrl, paramsMap);

        // server auth
        apiAuthencator.auth(url);
    }
}
