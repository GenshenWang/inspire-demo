package com.wgs.codedesign.享元模式.v2;


import com.wgs.codedesign.享元模式.Color;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wanggenshen
 * @date: 2020/4/3 15:54.
 * @description: 享元类
 */
public class ChessPieceUnitFactory {
    private static final Map<Integer, ChessPieceUnit> chessPieces = new HashMap<>();

    static {
        chessPieces.put(1, new ChessPieceUnit(1, "車", Color.BLACK));
        chessPieces.put(2, new ChessPieceUnit(2, "馬", Color.BLACK));
        chessPieces.put(3, new ChessPieceUnit(3, "象", Color.BLACK));
        chessPieces.put(4, new ChessPieceUnit(4, "士", Color.BLACK));
        chessPieces.put(5, new ChessPieceUnit(5, "将", Color.BLACK));
        chessPieces.put(6, new ChessPieceUnit(6, "士", Color.BLACK));
        chessPieces.put(7, new ChessPieceUnit(7, "象", Color.BLACK));
        chessPieces.put(8, new ChessPieceUnit(8, "馬", Color.BLACK));
        chessPieces.put(9, new ChessPieceUnit(9, "車", Color.BLACK));
        chessPieces.put(10, new ChessPieceUnit(10, "炮", Color.BLACK));
        chessPieces.put(11, new ChessPieceUnit(11, "炮", Color.BLACK));
        chessPieces.put(12, new ChessPieceUnit(12, "卒", Color.BLACK));
        chessPieces.put(13, new ChessPieceUnit(13, "卒", Color.BLACK));
        chessPieces.put(14, new ChessPieceUnit(14, "卒", Color.BLACK));
        chessPieces.put(15, new ChessPieceUnit(15, "卒", Color.BLACK));
        chessPieces.put(16, new ChessPieceUnit(16, "卒", Color.BLACK));



        chessPieces.put(17, new ChessPieceUnit(17, "車", Color.RED));
        chessPieces.put(18, new ChessPieceUnit(18, "馬", Color.RED));
        chessPieces.put(19, new ChessPieceUnit(19, "象", Color.RED));
        chessPieces.put(20, new ChessPieceUnit(20, "士", Color.RED));
        chessPieces.put(21, new ChessPieceUnit(21, "将", Color.RED));
        chessPieces.put(22, new ChessPieceUnit(22, "士", Color.RED));
        chessPieces.put(23, new ChessPieceUnit(23, "象", Color.RED));
        chessPieces.put(24, new ChessPieceUnit(24, "馬", Color.RED));
        chessPieces.put(25, new ChessPieceUnit(25, "車", Color.RED));
        chessPieces.put(26, new ChessPieceUnit(26, "炮", Color.RED));
        chessPieces.put(27, new ChessPieceUnit(27, "炮", Color.RED));
        chessPieces.put(28, new ChessPieceUnit(28, "卒", Color.RED));
        chessPieces.put(29, new ChessPieceUnit(29, "卒", Color.RED));
        chessPieces.put(30, new ChessPieceUnit(30, "卒", Color.RED));
        chessPieces.put(31, new ChessPieceUnit(31, "卒", Color.RED));
        chessPieces.put(32, new ChessPieceUnit(32, "卒", Color.RED));
    }

    public static ChessPieceUnit getChessPieceUnit(int id) {
        return chessPieces.get(id);
    }
}
