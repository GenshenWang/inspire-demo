package com.wgs.codedesign.快照模式.v2;

import java.util.Scanner;

/**
 * @author: wanggenshen
 * @date: 2020/4/30 00:58.
 * @description: XXX
 */
public class Main2 {

    public static void main(String[] args) {
        SnapShotHolderV2 snapShotHolderV2 = new SnapShotHolderV2();
        InputTextV2 inputTextV2 = new InputTextV2();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String inputText = sc.next();
            if (":list".equals(inputText)) {
                System.out.println(inputTextV2.getText());
            } else if (":undo".equals(inputText)) {
                Snapshot snapshot = snapShotHolderV2.popSnapshot();
                inputTextV2.restoreSnapshot(snapshot);
            } else {
                inputTextV2.append(inputText);
                inputTextV2.restoreSnapshot(inputTextV2.createSnapshot());
            }
        }
    }
}
