package com.wgs.codedesign.享元模式.v1;

import com.wgs.codedesign.享元模式.Color;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wanggenshen
 * @date: 2020/4/3 13:59.
 * @description: 棋局
 */
public class ChessBoard {

    private Map<Integer, ChessPiece> chessPieces = new HashMap<>();

    public ChessBoard() {
        initChessBoard();
    }

    private void initChessBoard() {
        chessPieces.put(1, new ChessPiece(1, "車", Color.BLACK, 0, 0));
        chessPieces.put(2, new ChessPiece(2, "馬", Color.BLACK, 1, 0));
        chessPieces.put(3, new ChessPiece(3, "象", Color.BLACK, 2, 0));
        chessPieces.put(4, new ChessPiece(4, "士", Color.BLACK, 3, 0));
        chessPieces.put(5, new ChessPiece(5, "将", Color.BLACK, 4, 0));
        chessPieces.put(6, new ChessPiece(6, "士", Color.BLACK, 5, 0));
        chessPieces.put(7, new ChessPiece(7, "象", Color.BLACK, 6, 0));
        chessPieces.put(8, new ChessPiece(8, "馬", Color.BLACK, 7, 0));
        chessPieces.put(9, new ChessPiece(9, "車", Color.BLACK, 8, 0));
        chessPieces.put(10, new ChessPiece(10, "炮", Color.BLACK, 1, 2));
        chessPieces.put(11, new ChessPiece(11, "炮", Color.BLACK, 7, 2));
        chessPieces.put(12, new ChessPiece(12, "卒", Color.BLACK, 0, 3));
        chessPieces.put(13, new ChessPiece(13, "卒", Color.BLACK, 2, 3));
        chessPieces.put(14, new ChessPiece(14, "卒", Color.BLACK, 4, 3));
        chessPieces.put(15, new ChessPiece(15, "卒", Color.BLACK, 6, 3));
        chessPieces.put(16, new ChessPiece(16, "卒", Color.BLACK, 8, 3));



        chessPieces.put(17, new ChessPiece(17, "車", Color.RED, 0, 9));
        chessPieces.put(18, new ChessPiece(18, "馬", Color.RED, 1, 9));
        chessPieces.put(19, new ChessPiece(19, "象", Color.RED, 2, 9));
        chessPieces.put(20, new ChessPiece(20, "士", Color.RED, 3, 9));
        chessPieces.put(21, new ChessPiece(21, "将", Color.RED, 4, 9));
        chessPieces.put(22, new ChessPiece(22, "士", Color.RED, 5, 9));
        chessPieces.put(23, new ChessPiece(23, "象", Color.RED, 6, 9));
        chessPieces.put(24, new ChessPiece(24, "馬", Color.RED, 7, 9));
        chessPieces.put(25, new ChessPiece(25, "車", Color.RED, 8, 9));
        chessPieces.put(26, new ChessPiece(26, "炮", Color.RED, 1, 7));
        chessPieces.put(27, new ChessPiece(27, "炮", Color.RED, 7, 7));
        chessPieces.put(28, new ChessPiece(28, "卒", Color.RED, 0, 6));
        chessPieces.put(29, new ChessPiece(29, "卒", Color.RED, 2, 6));
        chessPieces.put(30, new ChessPiece(30, "卒", Color.RED, 4, 6));
        chessPieces.put(31, new ChessPiece(31, "卒", Color.RED, 6, 6));
        chessPieces.put(32, new ChessPiece(32, "卒", Color.RED, 8, 6));
    }

    public void move(int chessPieceId, int toPositionX, int toPositionY) {
        ChessPiece chessPiece = chessPieces.get(chessPieceId);
        chessPiece.setPositionX(toPositionX);
        chessPiece.setPositionY(toPositionY);
    }
}
