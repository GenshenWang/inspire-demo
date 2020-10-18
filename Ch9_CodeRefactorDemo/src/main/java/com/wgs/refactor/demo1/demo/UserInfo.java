package com.wgs.refactor.demo1.demo;

import lombok.Data;

/**
 * 用户信息
 */
@Data
public class UserInfo {
    private long userId;
    private String userName;
    private String email;
    private String telephone;
    private long createTime;
    private long lastLoginTime;
    private String avatarUrl;

    // 用户地址信息
    private String province;
    private String city;
    private String region;
    private String detailAddress;
}
