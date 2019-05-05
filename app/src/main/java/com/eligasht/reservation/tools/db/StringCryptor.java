package com.eligasht.reservation.tools.db;

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


          /*  String signatureRawData = APPId+requestTimeStamp;

            // Sending side
            byte[] dataA = APIKey.getBytes("UTF-8");
            String base64 = Base64.encodeToString(dataA, Base64.DEFAULT);


            String secretAccessKey =APIKey;
            String data = "my data";
            byte[] secretKey = secretAccessKey.getBytes();
            SecretKeySpec signingKey = new SecretKeySpec(secretKey, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            byte[] bytes = data.getBytes();
            byte[] rawHmac = mac.doFinal(bytes);
            String base64 = Base64.encodeToString(bytes, Base64.DEFAULT);*/

/*
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] TDESKey = md.digest((salt + "tu89geji340t!@#$").getBytes("utf-8"));

           // String  uniqueID = UUID.randomUUID().toString();

            SecretKey key = new SecretKeySpec(TDESKey, "DESede");
            // IvParameterSpec iv = new IvParameterSpec(new byte[8]);
            Cipher TDESAlgorithm = Cipher.getInstance("DESede");
            TDESAlgorithm.init(Cipher.ENCRYPT_MODE, key);

            final byte[] plainTextBytes = text.getBytes("utf-8");
            final byte[] cipherText = TDESAlgorithm.doFinal(plainTextBytes);
            return Base64.encodeToString(cipherText, Base64.DEFAULT);*/
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
