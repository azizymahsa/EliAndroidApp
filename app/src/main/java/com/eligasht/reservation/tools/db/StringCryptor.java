package com.eligasht.reservation.tools.db;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class StringCryptor {
    public static String Encrypt(String text, String salt)
            throws NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException, UnsupportedEncodingException,
            IllegalBlockSizeException, BadPaddingException {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] TDESKey = md.digest((salt + "tu89geji340t!@#$").getBytes("utf-8"));



            SecretKey key = new SecretKeySpec(TDESKey, "DESede");
            // IvParameterSpec iv = new IvParameterSpec(new byte[8]);
            Cipher TDESAlgorithm = Cipher.getInstance("DESede");
            TDESAlgorithm.init(Cipher.ENCRYPT_MODE, key);

            final byte[] plainTextBytes = text.getBytes("utf-8");
            final byte[] cipherText = TDESAlgorithm.doFinal(plainTextBytes);
            return Base64.encodeToString(cipherText, Base64.DEFAULT);

            // BigInteger number = new BigInteger(1, messageDigest);
            // String md5 = number.toString(16);
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }

    public static String Decrypt(String text, String salt) {

        return "";
    }
}
