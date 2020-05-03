package com.wgs.codedesign.命令模式;

/**
 * @author: wanggenshen
 * @date: 2020/5/1 10:52.
 * @description: XXX
 */
public class GotDiamondCommand implements Command {

    private String data;

    public GotDiamondCommand(String data) {
        this.data = data;
    }

    @Override
    public void execute() {
        System.out.println("GotDiamondCommand receive data: " + data);
    }
}
