package com.wgs.codedesign.工厂模式.v4;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class IMsgHandlerManager<T> implements BeanPostProcessor {
    private Map<Integer, IMsgHandler> handlerMap = new ConcurrentHashMap<>();


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof IMsgHandler) {
            HandlerType[] handlerTypes = bean.getClass().getAnnotationsByType(HandlerType.class);
            if (handlerTypes != null && handlerTypes.length > 0) {
               for (HandlerType handlerType : handlerTypes) {
                   handlerMap.put(handlerType.handlerType().getCode(), (IMsgHandler) bean);
               }
            }
        }

        return bean;
    }

    public void doHandle(int type, T data) {
        IMsgHandler iMsgHandler = handlerMap.get(type);
        if (iMsgHandler == null) {
            throw new RuntimeException("Cannot find handler for type " + type);
        }
        iMsgHandler.processMsg(data);
    }

}
