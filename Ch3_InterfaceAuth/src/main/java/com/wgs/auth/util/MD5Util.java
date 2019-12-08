package com.wgs.auth.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanggenshen
 * Date: on 2019/12/8 09:40.
 * Description: MD5加密
 */
public class MD5Util {

    public static String encrypt(String input) throws Exception{
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException(e.toString());
        }

        try {
            byte[] byteArray = input.getBytes("UTF-8");
            byte[] md5Bytes = md5.digest(byteArray);
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception e) {
            throw new UnsupportedEncodingException(e.toString());
        }

    }

    public static void main(String[] args) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("url", "abc");
        map.put("name", "name");
        String mapStr = map.toString();
        String content1 = MD5Util.encrypt(mapStr);

        Map<String, String> map2 = new HashMap<>();
        map2.put("name", "name");
        map2.put("url", "abc");

        String mapStr2 = map2.toString();
        String content2 = MD5Util.encrypt(mapStr2);

        System.out.println(content1.equals(content2));

    }
}
