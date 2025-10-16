package com.bytehonor.sdk.framework.lang.regex;

import java.util.regex.Pattern;

import com.bytehonor.sdk.framework.lang.string.StringKit;

public class PatternKit {

    // private static final Logger LOG =
    // LoggerFactory.getLogger(PatternUtils.class);

    private static final String INTEGER_REG = "^-?[1-9]\\d*$";

    private static final String DOUBLE_REG = "^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$";

    // private static final String LETTER_REG = "[a-zA-Z]+";

    // private static final String CHINESE_REG = "[\u4e00-\u9fa5]";

    private static final Pattern INTEGER_PT = Pattern.compile(INTEGER_REG);
    private static final Pattern DOUBLE_PT = Pattern.compile(DOUBLE_REG);
    // private static final Pattern LETTER_PT = Pattern.compile(LETTER_REG);

    // private static final Pattern CHINESE_PT = Pattern.compile(CHINESE_REG);

    public static boolean isInteger(String str) {
        if (StringKit.isEmpty(str)) {
            return false;
        }
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (isNumberChar(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumberChar(char c) {
        if (c < '0' || c > '9') {
            return false;
        }

        return true;
    }

    public static boolean isNumber(String src) {
        if (StringKit.isEmpty(src)) {
            return false;
        }
        int len = src.length();
        for (int i = 0; i < len; i++) {
            if (isNumberChar(src.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumberValue(String str) {
        // 采用正则表达式的方式来判断一个字符串是否为数字，这种方式判断面比较全
        // 可以判断正负、整数小数

        return INTEGER_PT.matcher(str).find() || DOUBLE_PT.matcher(str).find();
    }

    public static boolean isLetterNumber(String src) {
        if (StringKit.isEmpty(src)) {
            return false;
        }
        int len = src.length();
        for (int i = 0; i < len; i++) {
            char ch = src.charAt(i);
            if (isLetterChar(ch) == false && isNumberChar(ch) == false) {
                return false;
            }
        }
        return true;
    }

    public static boolean isLetter(String src) {
        if (StringKit.isEmpty(src)) {
            return false;
        }
        int len = src.length();
        for (int i = 0; i < len; i++) {
            if (isLetterChar(src.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public static boolean isLetterChar(char c) {
        if (c < 'A' || c > 'z' || ('Z' < c && c < 'a')) {
            return false;
        }
        return true;
    }

    /**
     * 是否中文字符
     * 
     * @param c
     * @return
     */
    public static boolean isChineseChar(char c) {
        // 汉字基本集中在[19968,40869]之间,共有20901个汉字
        if (c < 19968 || c > 40869) {
            return false;
        }
        return true;
    }

    public static boolean isChinese(String src) {
        if (StringKit.isEmpty(src)) {
            return false;
        }
        int len = src.length();
        for (int i = 0; i < len; i++) {
            if (isChineseChar(src.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * 正常字符，数字、英文、中文
     * 
     * @param c
     * @return
     */
    public static boolean isNormalChar(char c) {
        return isNumberChar(c) || isLetterChar(c) || isChineseChar(c);
    }

    public static boolean isLetterStart(String src) {
        if (StringKit.isEmpty(src)) {
            return true;
        }

        return isLetterChar(src.charAt(0));
    }

    public static boolean isSpecial(String src) {
        if (StringKit.isEmpty(src)) {
            return true;
        }
        char c = src.charAt(0);
        if (c < 48) {
            return true;
        }
        if (c > 57 && c < 65) {
            return true;
        }

        if (c > 90 && c < 97) {
            return true;
        }

        if (c > 122 && c < 255) {
            return true;
        }

        String hexString = Integer.toHexString(c);
        if (hexString.equals("200b") || hexString.equals("200c") || hexString.equals("200d")) {
            return true;
        }

        return false;
    }

    // 字符串转换unicode
    public static String stringToUnicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i); // 取出每一个字符
            unicode.append("\\u" + Integer.toHexString(c));// 转换为unicode
        }
        return unicode.toString();
    }
}
