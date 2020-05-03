package com.wgs.codedesign.命令模式;

/**
 * @author: wanggenshen
 * @date: 2020/5/1 10:51.
 * @description: 命令模式就是将函数封装成对象, 这样就可以传递、保存、执行
 *               如:将execute()函数封装到Command类中, 后续就可以变成对象传递、执行
 */
public interface Command {
    void execute();
}
