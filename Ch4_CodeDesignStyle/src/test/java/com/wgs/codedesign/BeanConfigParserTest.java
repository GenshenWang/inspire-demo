package com.wgs.codedesign;

import com.wgs.codedesign.工厂模式.v3.*;
import com.wgs.codedesign.工厂模式.v3.bean.BeanDefinition;
import com.wgs.codedesign.工厂模式.v3.bean.XmlBeanConfigParser;
import com.wgs.codedesign.工厂模式.v3.model.RateLimiter;
import com.wgs.codedesign.工厂模式.v3.parser.BeanConfigParser;
import com.wgs.codedesign.工厂模式.v3.parser.DefaultResourceLoader;
import com.wgs.codedesign.工厂模式.v3.parser.ResourceLoader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * @author: wanggenshen
 * @date: 2020/3/25 09:42.
 * @description: XXX
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Ch4CodeDesignStyleApplication.class)
public class BeanConfigParserTest {

    public static final String FILE_LOCATION = "/beans.xml";

    @Test
    public void testMyClassPathXmlApplicationContext() {
        MyClassPathXmlApplicationContext applicationContext = new MyClassPathXmlApplicationContext(FILE_LOCATION);
        RateLimiter rateLimiter = (RateLimiter) applicationContext.getBean("rateLimiter");
        Assert.assertTrue(rateLimiter != null);
        Assert.assertTrue(rateLimiter.getRateConfig() != null);


    }
    @Test
    public void test() {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        InputStream in = null;
        try {
            in = resourceLoader.getResource(FILE_LOCATION);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BeanConfigParser configParser = new XmlBeanConfigParser();
        List<BeanDefinition> beanDefinitions = configParser.parse(in);
    }
}
