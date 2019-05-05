package com.eligasht.reservation.tools.db;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class RsaFunction {

    private static Key publicKey = loadPublicKey();

    private static Key loadPublicKey() {
        try {
            String pkey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDQZ0YVolLtPXXSowc6CopEXzGI/Tvri6hYT3KZ45G97DQn8ocydBQXSZfRR92MpiZHQn1zydpVBOCj7tUnSh1QoSN9aISG+tuLggYWdav6XlW2JAlduHkMbbyug9aoWIyaZjXXzyV+0e3hjwQhfTeQj9DLuGssWNX3YuYgf/e5LQIDAQAB";
            byte[] data = Base64.decode(pkey, Base64.DEFAULT);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            return fact.generatePublic(spec);
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] encrypt(byte[] plaintext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(plaintext);
    }

    public static String encBase64(String plaintext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        byte[] encBytes = encrypt(plaintext.getBytes("UTF-8"));
        return Base64.encodeToString(encBytes, Base64.DEFAULT);
    }

}