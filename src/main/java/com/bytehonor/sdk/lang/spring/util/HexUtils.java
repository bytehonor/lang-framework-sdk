package com.bytehonor.sdk.lang.spring.util;

import java.util.Objects;

public class HexUtils {

    /**
     * 字节数组倒序
     * 
     * @param bytes
     * @return
     */
    public static byte[] reverse(byte[] bytes) {
        Objects.requireNonNull(bytes, "bytes");
        int length = bytes.length;
        byte[] array = new byte[length];
        for (int i = 0; i < length; i++) {
            array[i] = bytes[length - 1 - i];
        }
        return array;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder("");
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        for (int i = 0; i < bytes.length; i++) {
            sb.append(byteToHex(bytes[i]));
        }
        return sb.toString();
    }

    public static String byteToHex(byte b) {
        StringBuilder sb = new StringBuilder("");
        int fb = b & 0xFF;
        String hex = Integer.toHexString(fb);
        if (hex.length() < 2) {
            sb.append(0);
        }
        sb.append(hex);
        return sb.toString();
    }

    /**
     * Convert hex string to byte[]
     * 
     * @param hexString the hex string
     * @return byte[]
     */
    public static byte[] hexToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toLowerCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * Convert char to byte
     * 
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789abcdef".indexOf(c);
    }
}
