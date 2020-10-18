package com.wgs.refactor.demo1.demo;

public class 重构变量_01 {

    /**
     * 00 - 重构命名不规范的变量
     *
     * Refactor - Rename
     * （shift+fn+f6）
     * @param name
     * @return
     */
    public String doSomething(String name) {
        System.out.println(name);

        name =  name + "b";
        name = name + "c";

        return this.sayHello(name);
    }

    private String sayHello(String a) {
        return "hello" + a;
    }
}
