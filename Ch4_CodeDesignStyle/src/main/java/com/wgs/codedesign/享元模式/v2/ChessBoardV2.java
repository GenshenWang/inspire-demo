package com.wgs.codedesign.享元模式.v2;


import java.util.HashMap;
import java.util.Map;

/**
 * @author: wanggenshen
 * @date: 2020/4/3 13:59.
 * @description: 棋局
 */
public class ChessBoardV2 {

    private Map<Integer, ChessPieceV2> chessPieces = new HashMap<>();

    public ChessBoardV2() {
        initChessBoard();
    }

    private void initChessBoard() {
        chessPieces.put(1, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(1), 0, 0));
        chessPieces.put(2, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(2), 1, 0));
        chessPieces.put(3, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(3), 2, 0));
        chessPieces.put(4, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(4), 3, 0));
        chessPieces.put(5, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(5), 4, 0));
        chessPieces.put(6, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(6), 5, 0));
        chessPieces.put(7, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(7), 6, 0));
        chessPieces.put(8, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(8), 7, 0));
        chessPieces.put(9, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(9), 8, 0));
        chessPieces.put(10, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(10), 1, 2));
        chessPieces.put(11, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(11), 7, 2));
        chessPieces.put(12, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(12), 0, 3));
        chessPieces.put(13, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(13), 2, 3));
        chessPieces.put(14, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(14), 4, 3));
        chessPieces.put(15, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(15), 6, 3));
        chessPieces.put(16, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(16), 8, 3));



        chessPieces.put(17, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(17), 0, 9));
        chessPieces.put(18, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(18), 1, 9));
        chessPieces.put(19, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(19), 2, 9));
        chessPieces.put(20, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(20), 3, 9));
        chessPieces.put(21, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(21), 4, 9));
        chessPieces.put(22, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(22), 5, 9));
        chessPieces.put(23, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(23), 6, 9));
        chessPieces.put(24, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(24), 7, 9));
        chessPieces.put(25, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(25), 8, 9));
        chessPieces.put(26, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(26), 1, 7));
        chessPieces.put(27, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(27), 7, 7));
        chessPieces.put(28, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(28), 0, 6));
        chessPieces.put(29, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(29), 2, 6));
        chessPieces.put(30, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(30), 4, 6));
        chessPieces.put(31, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(31), 6, 6));
        chessPieces.put(32, new ChessPieceV2(ChessPieceUnitFactory.getChessPieceUnit(32), 8, 6));
    }

    public void move(int chessPieceId, int toPositionX, int toPositionY) {
        ChessPieceV2 chessPiece = chessPieces.get(chessPieceId);
        chessPiece.setPositionX(toPositionX);
        chessPiece.setPositionY(toPositionY);
    }
}
