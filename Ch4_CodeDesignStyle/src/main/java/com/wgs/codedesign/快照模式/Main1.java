package com.wgs.codedesign.快照模式;

import java.util.Scanner;

/**
 * @author: wanggenshen
 * @date: 2020/4/29 22:42.
 * @description: XXX
 */
public class Main1 {

    public static void main(String[] args) {
        InputText inputText = new InputText();
        SnapShotHolder snapShotHolder = new SnapShotHolder();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.next();
            if (":list".equals(input)) {
                System.out.println(inputText.getText());
            } else if (":undo".equals(input)) {
                InputText snapshot = snapShotHolder.popSnapshot();
                inputText.setText(snapshot.getText());
            } else {
                // 先保存旧的
                snapShotHolder.pushSnapshot(inputText);
                inputText.append(input);
            }

        }
    }

}
