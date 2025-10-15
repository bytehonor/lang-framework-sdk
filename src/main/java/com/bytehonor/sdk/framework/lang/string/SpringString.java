package com.bytehonor.sdk.framework.lang.string;

import java.util.Objects;

import com.bytehonor.sdk.framework.lang.constant.CharConstants;
import com.bytehonor.sdk.framework.lang.constant.StringConstants;

/**
 * @author lijianqiang
 *
 */
public class SpringString {

    public static boolean isEmpty(String str) {
        return (str == null || str.isEmpty());
    }

    public static boolean equals(String src1, String src2) {
        if (src1 == null || src2 == null) {
            return false;
        }
        return Objects.equals(src1, src2);
    }

    public static String reverse(String src) {
        if (isEmpty(src)) {
            return StringConstants.EMPTY;
        }
        StringBuilder sb = new StringBuilder().append(src).reverse();
        return sb.toString();
    }

    /**
     * 字符串驼峰转下划线格式
     *
     * @param src 需要转换的字符串
     * @return 转换好的字符串
     */
    public static String camelToUnderline(String src) {
        if (isEmpty(src)) {
            return StringConstants.EMPTY;
        }
        int len = src.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = src.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i != 0) {
                    sb.append(CharConstants.UNDERLINE);
                }
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 字符串下划线转驼峰格式
     *
     * @param src 需要转换的字符串
     * @return 转换好的字符串
     */
    public static String underlineToCamel(String src) {
        if (isEmpty(src)) {
            return StringConstants.EMPTY;
        }
        String temp = src.toLowerCase();
        int len = temp.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = temp.charAt(i);
            if (c == CharConstants.UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(temp.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 首字母小写
     * 
     * @param str
     * @return
     */
    public static String uncapitalize(final String str) {
        final int strLen = length(str);
        if (strLen == 0) {
            return str;
        }

        final int firstCodepoint = str.codePointAt(0);
        final int newCodePoint = Character.toLowerCase(firstCodepoint);
        if (firstCodepoint == newCodePoint) {
            // already capitalized
            return str;
        }

        final int newCodePoints[] = new int[strLen]; // cannot be longer than the char array
        int outOffset = 0;
        newCodePoints[outOffset++] = newCodePoint; // copy the first codepoint
        for (int inOffset = Character.charCount(firstCodepoint); inOffset < strLen;) {
            final int codepoint = str.codePointAt(inOffset);
            newCodePoints[outOffset++] = codepoint; // copy the remaining ones
            inOffset += Character.charCount(codepoint);
        }
        return new String(newCodePoints, 0, outOffset);
    }

    public static int length(final CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }
}
