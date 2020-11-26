package com.wgs.seckill.interfaces.activity.command;

import lombok.Data;

import java.io.Serializable;

@Data
public class ActivityAccessContextDTO implements Serializable {

    private static final long serialVersionUID = 6020191229287540619L;

    private String cityId;
    /**
     * 门店id
     */
    private String storeId;
}
