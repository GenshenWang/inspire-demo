package com.wgs.codedesign.idgenerate.v2.impl;

import com.google.common.annotations.VisibleForTesting;
import com.sun.xml.internal.ws.util.StringUtils;
import com.wgs.codedesign.idgenerate.v2.IdGenerationFailureException;
import com.wgs.codedesign.idgenerate.v2.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * @author: wanggenshen
 * @date: 2020/3/8 11:04.
 * @description: XXX
 */
public class RandomIdGenerator implements IdGenerator {

    private static final Logger logger = LoggerFactory.getLogger(RandomIdGenerator.class);

    @Override
    public String generateId() throws IdGenerationFailureException {

        try {
            String subtractHostName = getLastFieldOfHostName();
            String randomSequenceChar = generateRandomChar(8);
            long curTimeStamp = System.currentTimeMillis();
            String id = String.format("%s-%d-%s", subtractHostName, curTimeStamp, randomSequenceChar);
            return id;
        } catch (Exception e) {
            throw new IdGenerationFailureException(e.toString());
        }

    }

    public static void main(String[] args) {
        try {
            String hostName = new RandomIdGenerator().generateId();
        } catch (IdGenerationFailureException e) {
            e.printStackTrace();
        }
    }


    private String getLastFieldOfHostName() throws UnknownHostException {
        String hostName = InetAddress.getLocalHost().getHostName();
        if (hostName == null || hostName.isEmpty()) {
            throw new UnknownHostException("Unknown host exception");
        }
        return getLastSubstrSplittedByDot(hostName);
    }

    @VisibleForTesting
    protected String getLastSubstrSplittedByDot(String hostName) {
        if (hostName == null || hostName.isEmpty()) {
            throw new IllegalArgumentException("hostName should not empty");
        }
        String[] tokens = hostName.split("\\.");
        return tokens[tokens.length - 1];
    }

    @VisibleForTesting
    protected String generateRandomChar(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Params length should older than 0");
        }
        // get random chars
        char[] randomChars = new char[length];
        int count = 0;
        Random random = new Random();
        while (count < length) {
            int maxAscii = 'z';
            int randomAscii = random.nextInt(maxAscii);
            boolean isDigit = randomAscii >= '0' && randomAscii <= '9';
            boolean isUpperChar = randomAscii >= 'A' && randomAscii <= 'Z';
            boolean isLowerChar = randomAscii >= 'a' && randomAscii <= 'z';
            if (isDigit || isUpperChar || isLowerChar) {
                randomChars[count] = (char) randomAscii;
                count++;
            }
        }
        return new String(randomChars);
    }

}
