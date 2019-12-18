package com.wgs.codedesign.ocp.v2.test;

import com.wgs.codedesign.ocp.v2.ApiAlert;
import com.wgs.codedesign.ocp.v2.ApiStatInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wanggenshen
 * Date: on 2019/12/11 20:03.
 * Description: XXX
 */
@Controller
public class TestController {

    @Autowired
    private ApiAlert apiAlert;

    @RequestMapping("/apiTest")
    @ResponseBody
    public void testApi(@RequestParam("api") String api) {
        ApiStatInfo apiStatInfo = new ApiStatInfo();
        apiStatInfo.setApi("getOrder");
        apiStatInfo.setRequestCount(8000);
        apiStatInfo.setErrorCount(9000L);
        apiStatInfo.setDurationSeconds(1L);

        apiAlert.check(apiStatInfo);
    }
}
