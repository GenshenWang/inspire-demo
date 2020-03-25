package com.wgs.codedesign.工厂模式.v3.bean;

/**
 * @author: wanggenshen
 * @date: 2020/3/25 18:12.
 * @description: XXX
 */
public class ClassFactory {

    public static Class createClass(String value) throws ClassNotFoundException {
        String type = "";
        if ("char".equals(value) || "Char".equals(value)) {
            type = "java.lang.Character";
        } else if ("short".equals(value) || "Short".equals(value)) {
            type = "java.lang.Short";
        } else if ("int".equals(value) || "Integer".equals(value)) {
            type = "java.lang.Integer";
        } else if ("long".equals(value) || "Long".equals(value)) {
            type = "java.lang.Long";
        } else if ("float".equals(value) || "Float".equals(value)) {
            type = "java.lang.Float";
        } else if ("double".equals(value) || "Double".equals(value)) {
            type = "java.lang.Double";
        } else if ("boolean".equals(value) || "Boolean".equals(value)) {
            type = "java.lang.Boolean";
        } else if ("String".equals(value)) {
            type = "java.lang.String";
        } else {
            throw new IllegalArgumentException("Can not find XML tag type: " + value);

        }

        return Class.forName(type);

    }
}
