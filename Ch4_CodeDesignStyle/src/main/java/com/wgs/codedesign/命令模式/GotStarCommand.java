package com.wgs.codedesign.命令模式;

/**
 * @author: wanggenshen
 * @date: 2020/5/1 10:54.
 * @description: XXX
 */
public class GotStarCommand implements Command {

    private String data;

    public GotStarCommand(String data) {
        this.data = data;
    }

    @Override
    public void execute() {
        System.out.println("GotStarCommand receive data: " + data);
    }
}
