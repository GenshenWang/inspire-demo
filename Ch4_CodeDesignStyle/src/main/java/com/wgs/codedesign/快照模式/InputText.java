package com.wgs.codedesign.快照模式;

/**
 * @author: wanggenshen
 * @date: 2020/4/29 22:30.
 * @description: XXX
 */
public class InputText {

    private final StringBuilder sb = new StringBuilder();

    public String getText() {
        return sb.toString();
    }

    public void append(String input) {
        sb.append(input);
    }


    public void setText(String text) {
        this.sb.replace(0, this.sb.length(), text);
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

        InputText inputText = (InputText) o;

        return sb != null ? sb.equals(inputText.sb) : inputText.sb == null;

    }

    @Override
    public int hashCode() {
        return sb != null ? sb.hashCode() : 0;
    }
}
