package com.wgs.codedesign.工厂模式.v3.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: wanggenshen
 * @date: 2020/3/24 21:55.
 * @description: XXX
 */
public class BeanFactory {

    private ConcurrentHashMap<String, Object> singletonInstances = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>();


    public Object getBean(String name) {
        BeanDefinition beanDefinition = beanDefinitions.get(name);
        if (beanDefinition == null) {
            throw new NoSuchBeanDefinitionException(name);
        }

        if (singletonInstances.containsKey(beanDefinition.getId())) {
            return singletonInstances.get(beanDefinition.getId());
        }

        return createBean(beanDefinition);
    }

    private Object createBean(BeanDefinition beanDefinition) {
        Object bean = null;

        try {
            Class beanClass = Class.forName(beanDefinition.getClassName());
            // xml中配置的bean构造器参数
            List<BeanDefinition.ConstructorArg> constructorArgs = beanDefinition.getConstructorArgs();
            // 无构造参数, 直接new
            if (constructorArgs.isEmpty()) {
                bean = beanClass.newInstance();
            } else {
                // 构造器参数的类型和value值
                Class[] argClasses = new Class[constructorArgs.size()];
                Object[] argValues = new Object[constructorArgs.size()];

                // 填充参数
                for (int i = 0; i < constructorArgs.size(); i++) {
                    BeanDefinition.ConstructorArg constructorArg = constructorArgs.get(i);
                    // 不是对象参数
                    if (!constructorArg.isRef()) {
                        argClasses[i] = constructorArg.getType();
                        argValues[i] = covertObject(constructorArg.getValue(), argClasses[i]);
                    } else {
                        Object refValue = constructorArg.getValue();
                        BeanDefinition refBeanDefinition = beanDefinitions.get(refValue);
                        if (refBeanDefinition == null) {
                            return new NoSuchBeanDefinitionException(refBeanDefinition.getId());
                        }

                        argClasses[i] = Class.forName(refBeanDefinition.getClassName());
                        argValues[i] = covertObject(createBean(refBeanDefinition), argClasses[i]);
                    }
                }

                bean = beanClass.getConstructor(argClasses).newInstance(argValues);

            }

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
                    | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Create bean failed: " + e.toString());
        } catch (Exception e) {
            throw new RuntimeException("Create bean failed: " + e.toString());
        }


        if (bean != null && beanDefinition.isSingleton()) {
            singletonInstances.putIfAbsent(beanDefinition.getId(), bean);
            return bean;
        }
        return bean;
    }

    private <T>  T covertObject(Object obj, Class clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        return (T) objectMapper.convertValue(obj, clazz);

    }

    public void addBeanDefinitions(List<BeanDefinition> beanDefinitions) {
        beanDefinitions.stream().forEach(beanDefinition -> {
            this.beanDefinitions.putIfAbsent(beanDefinition.getId(), beanDefinition);
        });

        beanDefinitions.stream().forEach(beanDefinition -> {
            // 立即加载 && 单例模式
            if (isNeedLoadRightNow(beanDefinition)) {
                if (singletonInstances.containsKey(beanDefinition.getId())) {
                    return;
                }
                createBean(beanDefinition);
            }
        });
    }

    private boolean isNeedLoadRightNow(BeanDefinition beanDefinition) {
        return beanDefinition.isLazyInit() == false && beanDefinition.isSingleton();
    }


}
