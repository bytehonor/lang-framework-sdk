package com.bytehonor.sdk.lang.bytehonor.util;

public class LongIdUtils {

    private static final long BASE = 20180328L;

    private static final int SCALE_62 = 62;

    private static final String CHAR_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static final char[] CHARS = CHAR_STRING.toCharArray();

    // 数字转62进制
    public static String encode(long num) {
        num += BASE;
        StringBuilder sb = new StringBuilder();
        int remainder;
        int limit = SCALE_62 - 1;
        while (num > limit) {
            // 对 scale 进行求余，然后将余数追加至 sb 中，由于是从末位开始追加的，因此最后需要反转字符串
            remainder = Long.valueOf(num % SCALE_62).intValue();
            sb.append(CHARS[remainder]);
            // 除以进制数，获取下一个末尾数
            num = num / SCALE_62;
        }
        sb.append(CHARS[Long.valueOf(num).intValue()]);
        return sb.reverse().toString();
    }

    // 62进制转为数字
    public static long decode(String str) {
        // 将 0 开头的字符串进行替换
        str = str.replace("^0*", "");
        long value = 0;
        char tempChar;
        int tempCharValue;
        int length = str.length();
        for (int i = 0; i < length; i++) {
            // 获取字符
            tempChar = str.charAt(i);
            // 单字符值
            tempCharValue = CHAR_STRING.indexOf(tempChar);
            // 单字符值在进制规则下表示的值
            value += (long) (tempCharValue * Math.pow(SCALE_62, length - i - 1));
        }
        return value - BASE;
    }
}
