package com.bytehonor.sdk.basic.lang.util;

public class LongIdUtils {

    public static final long MAX = 999999999999L; // 12bits

    public static String encode(long num) {
        long val = MAX - num;
        return Long.toString(val, 36);
    }

    public static long decode(String src) {
        long val = Long.valueOf(src, 36);
        return MAX - val;
    }
}
