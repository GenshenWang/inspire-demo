package com.wgs.codedesign.享元模式.v1;

import com.wgs.codedesign.享元模式.Color;

/**
 * @author: wanggenshen
 * @date: 2020/4/3 13:57.
 * @description: 棋子类
 */
public class ChessPiece {
    private int id;
    private String text;
    private Color color;
    private int positionX;
    private int positionY;

    public ChessPiece(int id, String text, Color color, int positionX, int positionY) {
        this.id = id;
        this.text = text;
        this.color = color;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
