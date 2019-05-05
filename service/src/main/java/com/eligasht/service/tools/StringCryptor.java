package com.eligasht.service.tools;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class StringCryptor {
    public static String Encrypt(String text, String salt)
        throws NoSuchPaddingException, InvalidKeyException,
        InvalidAlgorithmParameterException, UnsupportedEncodingException,
        IllegalBlockSizeException, BadPaddingException {

    try {
        Long currentDate=System.currentTimeMillis();
        //  String source =  Long.toString(currentDate/1000);
        String source =  "123456";


        final String MD5 = "MD5";

        // Create MD5 Hash
        MessageDigest digest = java.security.MessageDigest
                .getInstance(MD5);
        digest.update(source.getBytes());
        byte messageDigest[] = digest.digest();

        // Create Hex String
        StringBuilder hexString = new StringBuilder();
        for (byte aMessageDigest : messageDigest) {
            String h = Integer.toHexString(0xFF & aMessageDigest);
            while (h.length() < 2)
                h = "0" + h;
            hexString.append(h);
        }
        return "amx "+hexString.toString();


    } catch (NoSuchAlgorithmException ex) {
        return null;
    }
}

        public static String Decrypt(String text, String salt) {

            return "";
        }
}
