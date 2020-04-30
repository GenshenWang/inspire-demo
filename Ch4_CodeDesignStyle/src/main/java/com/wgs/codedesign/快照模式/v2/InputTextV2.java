package com.wgs.codedesign.快照模式.v2;

/**
 * @author: wanggenshen
 * @date: 2020/4/29 22:30.
 * @description: XXX
 */
public class InputTextV2 {

    private final StringBuilder sb = new StringBuilder();

    public String getText() {
        return sb.toString();
    }

    public void append(String input) {
        sb.append(input);
    }


    public Snapshot createSnapshot() {
        return new Snapshot(sb.toString());
    }

    public void restoreSnapshot(Snapshot snapshot) {
        this.sb.replace(0, this.sb.length(), snapshot.getText());
    }

    @Override
    public String toString() {
        return "InputText{" +
                "sb=" + sb +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InputTextV2 inputText = (InputTextV2) o;

        return sb != null ? sb.equals(inputText.sb) : inputText.sb == null;

    }

    @Override
    public int hashCode() {
        return sb != null ? sb.hashCode() : 0;
    }
}
