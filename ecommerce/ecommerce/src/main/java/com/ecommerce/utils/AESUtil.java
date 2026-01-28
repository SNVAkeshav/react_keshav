package com.ecommerce.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtil {
    private static final String AES = "AES";
    private static final String AES_GCM_NO_PADDING = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH = 16 * 8;

    // Replace with your own secret key, base64 encoded
    private static final String SECRET_KEY = "w4Z7t+X1xk6uG4q8HVJFcE3XxXH8mH7AwVSTKX2QZog=";

    // Replace with your own 12 byte IV (Initialization Vector), base64 encoded
    private static final String IV = "bWlndHR5aXYxMjM=";

    private static SecretKey getSecretKey() {
        byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, AES);
    }

    private static byte[] getIV() {
        return Base64.getDecoder().decode(IV);
    }

    public static String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, getIV());
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(), spec);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, getIV());
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), spec);
        byte[] decoded = Base64.getDecoder().decode(encryptedData);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted);
    }
}

