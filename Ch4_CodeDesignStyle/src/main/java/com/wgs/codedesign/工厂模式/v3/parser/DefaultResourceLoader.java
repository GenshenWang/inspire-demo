package com.wgs.codedesign.工厂模式.v3.parser;

import org.springframework.util.Assert;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author: wanggenshen
 * @date: 2020/3/24 18:14.
 * @description: XXX
 */
public class DefaultResourceLoader implements ResourceLoader {

    @Override
    public InputStream getResource(String location) throws FileNotFoundException {
        Assert.notNull(location, "Location should not be null");
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            location = location.substring(CLASSPATH_URL_PREFIX.length());
        }
        if (!location.startsWith("/")) {
            location = "/" + location;
        }
        InputStream in = this.getClass().getResourceAsStream(location);
        if (in == null) {
            throw new FileNotFoundException("Can not find config file : " + location);
        }
        return in;

    }
}
