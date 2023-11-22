package com.coursework;

import org.springframework.util.DigestUtils;

public class MD5Utils {

    public static String generateMD5(String input) {
        return DigestUtils.md5DigestAsHex(input.getBytes());
    }

    public static void main(String[] args) {
        String input = "password";
        String md5Hash = generateMD5(input);
        System.out.println("MD5 Hash: " + md5Hash);
    }
}
