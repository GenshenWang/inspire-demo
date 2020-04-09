package com.wgs.codedesign.模板模式.v1_经典代码实现;

/**
 * @author: wanggenshen
 * @date: 2020/4/9 19:46.
 * @description: 模板模式的经典代码实现
 */
public abstract class AbstractTemplateClass {

    /**
     * 模板方法, 使用final避免子类重写
     */
    public final void templateMethod() {

        System.out.println("模板方法开始执行");

        // 由子类去重写
        method1();

        method2();

        System.out.println("模板方法执行结束");

    }

    protected abstract void method2();

    protected abstract void method1();

    // 测试
    public static void main(String[] args) {
        AbstractTemplateClass templateClass = new ConcreteClass1();
        templateClass.templateMethod();

        AbstractTemplateClass templateClass2 = new ConcreteClass2();
        templateClass2.templateMethod();
    }

}

class ConcreteClass1 extends AbstractTemplateClass{

    @Override
    protected void method2() {
        System.out.println("类1执行模板方法2");
    }

    @Override
    protected void method1() {
        System.out.println("类1执行模板方法1");

    }
}

class ConcreteClass2 extends AbstractTemplateClass{

    @Override
    protected void method2() {
        System.out.println("类2执行模板方法2");
    }

    @Override
    protected void method1() {
        System.out.println("类2执行模板方法1");

    }
}
