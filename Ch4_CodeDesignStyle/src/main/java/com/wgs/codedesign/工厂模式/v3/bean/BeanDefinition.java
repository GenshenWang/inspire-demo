package com.wgs.codedesign.工厂模式.v3.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wanggenshen
 * @date: 2020/3/24 17:03.
 * @description:
 *
 * beans.xml支持的参数有:
 * id: bean id
 * className: bean class
 * constructorArgs: bean constructor args
 *      - type:  constructor arg type
 *      - isRef: is need depended on other beans.
 *      - value: the constructor arg value or ref value.
 * lazyInit: if set true, the bean instance of class will create until use it.
 *           else false, will be created when the programme started.
 *  scope:  the bean is singleton or prototype. Default value is singleton.
 *
 */
public class BeanDefinition {
    private String id;
    private String className;
    private List<ConstructorArg> constructorArgs = new ArrayList<>();
    private boolean lazyInit;
    private Scope scope = Scope.SINGLETON;


    public BeanDefinition(String id, String className, List<ConstructorArg> constructorArgs, boolean lazyInit, Scope scope) {
        this.id = id;
        this.className = className;
        this.constructorArgs = constructorArgs;
        this.lazyInit = lazyInit;
        this.scope = scope;
    }

    public BeanDefinition() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ConstructorArg> getConstructorArgs() {
        return constructorArgs;
    }

    public void setConstructorArgs(List<ConstructorArg> constructorArgs) {
        this.constructorArgs = constructorArgs;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    static enum Scope {
        SINGLETON,
        PROTOTYPE
    }

    public boolean isSingleton() {
        return Scope.SINGLETON.equals(this.scope);
    }

    static class ConstructorArg {
        private Class type;
        private Object value;
        private boolean isRef;

        public Class getType() {
            return type;
        }

        public void setType(Class type) {
            this.type = type;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public boolean isRef() {
            return isRef;
        }

        public void setRef(boolean ref) {
            isRef = ref;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BeanDefinition that = (BeanDefinition) o;

        if (lazyInit != that.lazyInit) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (className != null ? !className.equals(that.className) : that.className != null) return false;
        if (constructorArgs != null ? !constructorArgs.equals(that.constructorArgs) : that.constructorArgs != null)
            return false;
        return scope == that.scope;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (constructorArgs != null ? constructorArgs.hashCode() : 0);
        result = 31 * result + (lazyInit ? 1 : 0);
        result = 31 * result + (scope != null ? scope.hashCode() : 0);
        return result;
    }
}
