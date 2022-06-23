package com.bytehonor.sdk.lang.spring.util;

import com.bytehonor.sdk.define.spring.constant.CharConstants;
import com.bytehonor.sdk.define.spring.constant.StringConstants;

/**
 * @author lijianqiang
 *
 */
public class StringObject {

    public static boolean isEmpty(String str) {
        return (str == null || str.isEmpty());
    }

    public static boolean equals(String src1, String src2) {
        if (src1 == null || src2 == null) {
            return false;
        }
        return src1.equals(src2);
    }

    /**
     * 字符串驼峰转下划线格式
     *
     * @param src 需要转换的字符串
     * @return 转换好的字符串
     */
    public static String camelToUnderline(String src) {
        if (StringObject.isEmpty(src)) {
            return StringConstants.EMPTY;
        }
        int len = src.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = src.charAt(i);
            if (Character.isUpperCase(c) && i > 0) {
                sb.append(CharConstants.UNDERLINE);
            }
            sb.append(Character.toLowerCase(c));
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
        if (StringObject.isEmpty(src)) {
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
}
