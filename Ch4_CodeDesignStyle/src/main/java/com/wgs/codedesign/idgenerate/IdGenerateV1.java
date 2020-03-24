package com.wgs.codedesign.idgenerate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * @author: wanggenshen
 * @date: 2020/3/7 13:31.
 * @description: XXX
 */
public class IdGenerateV1 {
    private static final Logger logger = LoggerFactory.getLogger(IdGenerateV1.class);

    public static String generate() {

        // get hostName
        String hostName = "";
        try {
            hostName = InetAddress.getLocalHost().getHostName();
            String[] tokens = hostName.split("\\.");
            if (tokens.length > 0) {
                hostName = tokens[tokens.length - 1];
            }
        } catch (Exception e) {
            logger.warn("Failed to get the host name.", e);
            hostName = "unknown";
        }

        // get random chars
        char[] randomChars = new char[8];
        int count = 0;
        Random random = new Random();
        while (count < 8) {
            int randomAscii = random.nextInt(122);
            int randomCharInt = getRandomChar(randomAscii);
            if (randomCharInt == -1) {
                continue;
            }
            randomChars[count] = (char) randomCharInt;
            count++;
        }

        // get current time
        long currentTime = System.currentTimeMillis();

        String id = String.format("%s-%d-%s", hostName, currentTime, new String(randomChars));

        return id;
    }

    private static int getRandomChar(int randomAscii) {
        if (randomAscii >= 48 && randomAscii <= 57) {
            return '0' + (randomAscii - 48);
        } else if (randomAscii >= 65 && randomAscii <= 90) {
            return 'A' + (randomAscii - 65);
        } else if (randomAscii >= 97 && randomAscii <= 122) {
            return 'a' + (randomAscii - 97);
        }
        return -1;

    }

    public static void main(String[] args) {
        String id = generate();
        System.out.println(id);

        id = generate();
        System.out.println(id);

        id = generate();
        System.out.println(id);
    }
}
