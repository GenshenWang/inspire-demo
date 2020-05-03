package com.wgs.codedesign.命令模式;

/**
 * @author: wanggenshen
 * @date: 2020/5/1 10:55.
 * @description: XXX
 */
public class GotArrowCommand implements Command {
    private String data;

    public GotArrowCommand(String data) {
        this.data = data;
    }

    @Override
    public void execute() {
        System.out.println("GotArrowCommand receive data: " + data);
    }
}
