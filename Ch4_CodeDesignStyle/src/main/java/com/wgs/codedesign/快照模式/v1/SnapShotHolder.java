package com.wgs.codedesign.快照模式.v1;

import java.util.Stack;

/**
 * @author: wanggenshen
 * @date: 2020/4/29 22:32.
 * @description: XXX
 */
public class SnapShotHolder {

    private Stack<InputText> snapshots = new Stack();

    public InputText popSnapshot() {
        return snapshots.pop();
    }

    public void pushSnapshot(InputText inputText) {
        InputText deepCloned = new InputText();
        deepCloned.setText(inputText.getText());
        snapshots.push(deepCloned);
    }


}
