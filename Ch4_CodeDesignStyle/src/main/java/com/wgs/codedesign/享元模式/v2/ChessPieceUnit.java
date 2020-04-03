package com.wgs.codedesign.享元模式.v2;

import com.wgs.codedesign.享元模式.Color;

/**
 * @author: wanggenshen
 * @date: 2020/4/3 15:54.
 * @description: 享元类
 */
public class ChessPieceUnit {
    private int id;
    private String text;
    private Color color;

    public ChessPieceUnit(int id, String text, Color color) {
        this.id = id;
        this.text = text;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Color getColor() {
        return color;
    }
}
