package com.wgs.codedesign.享元模式.v2;

import com.wgs.codedesign.享元模式.Color;

/**
 * @author: wanggenshen
 * @date: 2020/4/3 13:57.
 * @description: 棋子类
 */
public class ChessPieceV2 {
    private ChessPieceUnit chessPieceUnit;
    private int positionX;
    private int positionY;

    public ChessPieceV2(ChessPieceUnit chessPieceUnit, int positionX, int positionY) {
        this.chessPieceUnit = chessPieceUnit;
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

