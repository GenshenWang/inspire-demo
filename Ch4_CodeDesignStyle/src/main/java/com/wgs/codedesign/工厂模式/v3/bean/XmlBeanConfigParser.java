package com.wgs.codedesign.工厂模式.v3.bean;

import com.google.common.collect.Lists;
import com.wgs.codedesign.工厂模式.v3.parser.ResourceReader;
import com.wgs.codedesign.工厂模式.v3.parser.SAXResourceReader;
import com.wgs.codedesign.工厂模式.v3.parser.BeanConfigParser;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.*;

/**
 * @author: wanggenshen
 * @date: 2020/3/24 17:48.
 * @description: 根据beans.xml文件的输入流转换成Bean结构
 *
 * 待解析beans.xml文件结构
 * beans
 *      |
 *               |- id
 *      ---bean -|- class
 *              -|- lazy-init
 *              -|- scope
 *           |
 *           |                   -|- type
 *           ----constructor-arg -|- value
 *                               -|- ref
 *
 *  首先读取beans标签, 获取beans标签下的bean标签;
 *  再递归获取bean标签的标签元素: id、class、lazy-init、scope;
 *    再递归获取bean标签下constructor-arg的标签元素: type、value、ref
 *
 */
public class XmlBeanConfigParser implements BeanConfigParser {

    private ResourceReader resourceReader;
    private static final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    public XmlBeanConfigParser() {
        this.resourceReader = new SAXResourceReader();
    }

    @Override
    public List<BeanDefinition> parse(InputStream inputStream) {

        Document document = null;
        try {
            document = resourceReader.read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid file config");
        }

        // 获取根节点
        Element rootElement = document.getRootElement();

        List<BeanDefinition> beanDefinitions = new ArrayList<>();
        try {
            recursiveGetNodes(rootElement, beanDefinitions);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return beanDefinitions;
    }

    /**
     * 2. 获取节点信息
     *
     * @param rootElement
     */
    protected void recursiveGetNodes(Element rootElement,  List<BeanDefinition> beanDefinitions) throws ClassNotFoundException {

        String rootElementName = rootElement.getName();
        System.out.println("**********获取当前名称*******:" + rootElementName);


        // 获取属性信息
        List<Attribute> attributes = rootElement.attributes();
        for (Attribute attribute : attributes) {
            System.out.println("属性标签:" + attribute.getName() + ", 属性值:" + attribute.getText());
            String attrKey = attribute.getName();
            String attrValue = attribute.getText();

            String beanId = findBeanTagId(rootElement);
            // bean 为父级元素<beans>下的一级元素
            if ("bean".equals(rootElement.getName())) {
                BeanDefinition beanDefinition = beanDefinitionMap.get(beanId);
                beanDefinition = beanDefinition == null ?  new BeanDefinition() :  beanDefinition;

                if ("id".equals(attrKey)) {
                    beanDefinition.setId(attrValue);
                } else if ("class".equals(attrKey)) {
                    beanDefinition.setClassName(attrValue);
                } else if ("lazy-init".equals(attrKey)) {
                    beanDefinition.setLazyInit("true".equals(attrValue));
                } else if ("scope".equals(attrKey)) {
                    beanDefinition.setScope("singleton".equals(attrValue) ? BeanDefinition.Scope.SINGLETON : BeanDefinition.Scope.PROTOTYPE);
                }

                if (!beanDefinitions.contains(beanDefinition)) {
                    beanDefinitions.add(beanDefinition);
                    beanDefinitionMap.putIfAbsent(beanId, beanDefinition);

                }
            }
            // bean 为父级元素<beans>下的二级元素
            else if ("constructor-arg".equals(rootElement.getName())) {

                BeanDefinition beanDefinition = beanDefinitionMap.get(beanId);
                BeanDefinition.ConstructorArg constructArg = null;
                if ("ref".equals(attrKey)) {
                    constructArg = new BeanDefinition.ConstructorArg.Builder()
                            .isRef(true)
                            .value(attrValue)
                            .build();
                } else if("type".equals(attrKey)) {
                    constructArg = new BeanDefinition.ConstructorArg.Builder()
                            .isRef(false)
                            .type(ClassFactory.createClass(rootElement.attribute("type").getValue()))
                            .value(rootElement.attribute("value").getValue())
                            .build();
                }
                
                if (CollectionUtils.isEmpty(beanDefinition.getConstructorArgs())) {
                    beanDefinition.setConstructorArgs(Lists.newArrayList(constructArg));
                } else {
                    beanDefinition.getConstructorArgs().add(constructArg);
                }

                break;

            }

        }


        System.out.println("=============");

        // 使用迭代器遍历,继续遍历子节点
        Iterator<Element> elementIterator = rootElement.elementIterator();
        while (elementIterator.hasNext()) {
            Element next = elementIterator.next();
            recursiveGetNodes(next, beanDefinitions);
        }
    }


    /**
     * 获取父元素<beans>下的<bean>标签下的 'id'属性
     *
     * @param rootElement
     * @return
     */
    private String findBeanTagId(Element rootElement) {
        if (rootElement.isRootElement()) {
            return null;
        }
        while (!rootElement.getParent().isRootElement()) {
            rootElement = rootElement.getParent();
        }
        return rootElement.attribute("id").getValue();

    }
}
