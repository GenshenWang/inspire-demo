package com.wgs.codedesign.工厂模式.v3;

import com.wgs.codedesign.工厂模式.v3.bean.BeanDefinition;
import com.wgs.codedesign.工厂模式.v3.bean.BeanFactory;
import com.wgs.codedesign.工厂模式.v3.bean.XmlBeanConfigParser;
import com.wgs.codedesign.工厂模式.v3.parser.BeanConfigParser;
import com.wgs.codedesign.工厂模式.v3.parser.DefaultResourceLoader;
import com.wgs.codedesign.工厂模式.v3.parser.ResourceLoader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author: wanggenshen
 * @date: 2020/3/24 17:01.
 * @description: 执行入口
 */
public class MyClassPathXmlApplicationContext implements MyApplicationContext {

    private ResourceLoader resourceLoader;
    private BeanConfigParser beanConfigParser;
    private BeanFactory beanFactory;

    public MyClassPathXmlApplicationContext(String configLocation) {
        // init
        this.beanConfigParser = new XmlBeanConfigParser();
        this.resourceLoader = new DefaultResourceLoader();
        this.beanFactory = new BeanFactory();

        loadBeanDefinitions(configLocation);
    }

    private void loadBeanDefinitions(String configLocation) {
        InputStream in = null;
        try {
            in = resourceLoader.getResource(configLocation);

            if (in == null) {
                throw new RuntimeException("FileNode config is not exits: " + configLocation);
            }

            List<BeanDefinition> beanDefinitions = beanConfigParser.parse(in);
            beanFactory.addBeanDefinitions(beanDefinitions);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public Object getBean(String name) {
        return beanFactory.getBean(name);
    }
}
