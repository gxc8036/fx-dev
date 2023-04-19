package com.example.fxdev.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * add by gus.
 * 19951799881@163.com
 */
public class AESUtils {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String CHARSET = "UTF-8";
    private static final int KEY_SIZE = 128;

    private static SecretKeySpec secretKey;

    public static void main(String[] args) throws Exception {
        String key = "626A15B3290B5F42439B37FDD51A4F65";
        String plainText = "Hello, world!";
        String cipherText = encrypt(plainText, key);
        System.out.println("Encrypted text: " + cipherText);
        String decryptedText = decrypt(cipherText, key);
        System.out.println("Decrypted text: " + decryptedText);

        String s = "Hello, world!";
        String s1 = bytesToHex(s.getBytes());
        System.out.println(s1);

        String s2 = new String(hexToBytes(s1));
        System.out.println(s2);
    }

    public static String encrypt(String plainText, String key) throws Exception {
        byte[] keyBytes = key.getBytes();
        secretKey = new SecretKeySpec(keyBytes, 0, KEY_SIZE / 8, ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] cipherText = cipher.doFinal(plainText.getBytes(CHARSET));
        return bytesToHex(cipherText);
    }

    public static String decrypt(String cipherText, String key) throws Exception {
        byte[] keyBytes = key.getBytes();
        secretKey = new SecretKeySpec(keyBytes, 0, KEY_SIZE / 8, ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] plainText = cipher.doFinal(hexToBytes(cipherText));
        return new String(plainText, CHARSET);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));
        }
        return result.toString();
    }

    private static byte[] hexToBytes(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}
