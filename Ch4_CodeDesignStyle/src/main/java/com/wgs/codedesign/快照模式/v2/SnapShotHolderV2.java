package com.wgs.codedesign.快照模式.v2;

import java.util.Stack;

/**
 * @author: wanggenshen
 * @date: 2020/4/29 22:32.
 * @description: XXX
 */
public class SnapShotHolderV2 {

    private Stack<Snapshot> snapshots = new Stack();

    public Snapshot popSnapshot() {
        return snapshots.pop();
    }

    public void pushSnapshot(Snapshot snapshot) {
        Snapshot deepCloned = new Snapshot(snapshot.getText());
        snapshots.push(deepCloned);
    }


}
