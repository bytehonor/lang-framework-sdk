package com.bytehonor.sdk.lang.spring.string;

import java.util.regex.Pattern;

import com.bytehonor.sdk.define.spring.constant.CharConstants;
import com.bytehonor.sdk.define.spring.util.StringObject;
import com.bytehonor.sdk.lang.spring.regex.PatternUtils;

public class StringRemoveUtils {

    /**
     * 大写 P 表示 Unicode 字符集七个字符属性之一：标点字符。
     * 
     * 其他六个是
     * 
     * L：字母； M：标记符号（一般不会单独出现）； Z：分隔符（比如空格、换行等）； S：符号（比如数学符号、货币符号等）；
     * N：数字（比如阿拉伯数字、罗马数字等）； C：其他字符
     */

    private static final String PS = "\\pP|\\pS";
    private static final Pattern PS_PATTERN = Pattern.compile(PS);

    private static final String PSZ = "\\pP|\\pS|\\pZ";
    private static final Pattern PSZ_PATTERN = Pattern.compile(PSZ);
    
    /**
     * 移除指定范围内的字串，左闭右闭
     * 
     * @param text
     * @param from
     * @param to
     * @return
     */
    public static String removeByFromTo(String text, int from, int to) {
        if (to < from) {
            throw new RuntimeException("from to invalid");
        }
        if (from < 0) {
            from = 0;
        }
        if (to < 0) {
            to = 0;
        }
        int length = text.length();
        if (to > length) {
            to = length;
        }
        char[] target = new char[length * 2];
        int total = 0;
        for (int i = 0; i < length; i++) {
            if (from <= i && i <= to) {
                continue;
            }
            target[total++] = text.charAt(i);
        }
        if (total > 0) {
            return new String(target, 0, total);
        }

        return "";
    }

    /**
     * 清除标点符号
     * 
     * @param src
     * @return clear
     */
    public static String cleanPS(String src) {
        if (StringObject.isEmpty(src)) {
            return "";
        }
        // return src.replaceAll(PSZ, "");
        return PS_PATTERN.matcher(src).replaceAll("");
    }

    /**
     * 清除标点符号
     * 
     * @param src
     * @return clear
     */
    public static String cleanPSZ(String src) {
        if (StringObject.isEmpty(src)) {
            return "";
        }
        // return src.replaceAll(PSZ, "");
        return PSZ_PATTERN.matcher(src).replaceAll("");
    }

    /**
     * 替换非中文字符为空格
     * 
     * @param src
     * @return
     */
    public static String replaceNonChineseWithBlank(String src) {
        if (StringObject.isEmpty(src)) {
            return "";
        }
        int length = src.length();
        char[] target = new char[length];
        int at = 0;
        boolean hasInsertBlank = false;
        for (int i = 0; i < length; i++) {
            if (PatternUtils.isChineseChar(src.charAt(i))) {
                target[at++] = src.charAt(i);
                hasInsertBlank = false;
            } else {
                if (at == 0 || hasInsertBlank) {
                    continue;
                }
                hasInsertBlank = true;
                target[at++] = CharConstants.BLANK;
            }
        }
        if (at > 0 && CharConstants.BLANK == target[at - 1]) {
            at--;
        }
        if (at > 0) {
            return new String(target, 0, at);
        }
        return "";
    }

    /**
     * 除去非中文字符
     * 
     * @param src
     * @return
     */
    public static String removeNonChinese(String src) {
        if (StringObject.isEmpty(src)) {
            return "";
        }
        int length = src.length();
        char[] target = new char[length];
        int total = 0;
        for (int i = 0; i < length; i++) {
            if (PatternUtils.isChineseChar(src.charAt(i))) {
                target[total++] = src.charAt(i);
            }
        }
        if (total > 0) {
            return new String(target, 0, total);
        }
        return "";
    }

    /**
     * 替换非Normal字符为空格
     * 
     * @param src
     * @return
     */
    public static String replaceNonNormalWithBlank(String src) {
        if (StringObject.isEmpty(src)) {
            return "";
        }
        int length = src.length();
        char[] target = new char[length];
        int at = 0;
        boolean hasInsertBlank = false;
        for (int i = 0; i < length; i++) {
            if (PatternUtils.isNormalChar(src.charAt(i))) {
                target[at++] = src.charAt(i);
                hasInsertBlank = false;
            } else {
                if (at == 0 || hasInsertBlank) {
                    continue;
                }
                hasInsertBlank = true;
                target[at++] = CharConstants.BLANK;
            }
        }
        if (at > 0 && CharConstants.BLANK == target[at - 1]) {
            at--;
        }
        if (at > 0) {
            return new String(target, 0, at);
        }
        return "";
    }

    /**
     * 除去非Normal字符
     * 
     * @param src
     * @return
     */
    public static String removeNonNormal(String src) {
        if (StringObject.isEmpty(src)) {
            return "";
        }
        int length = src.length();
        char[] target = new char[length];
        int total = 0;
        for (int i = 0; i < length; i++) {
            if (PatternUtils.isNormalChar(src.charAt(i))) {
                target[total++] = src.charAt(i);
            }
        }
        if (total > 0) {
            return new String(target, 0, total);
        }
        return "";
    }

    /**
     * 替换{regex}字符为空格
     * 
     * @param src
     * @return
     */
    public static String replaceRegexWithBlank(String src, String regex) {
        if (StringObject.isEmpty(src)) {
            return "";
        }
        if (StringObject.isEmpty(regex)) {
            return src;
        }
        int regLen = regex.length();
        char[] regChars = regex.toCharArray();
        char[] source = src.toCharArray();
        int length = src.length();
        char[] result = new char[length];
        int at = 0;
        boolean hasInsertBlank = false;
        for (int i = 0; i < length; i++) {
            if (regChars[0] == src.charAt(i) && isEquals(source, i, length, regChars, regLen)) {
                if (hasInsertBlank) {
                    continue;
                }
                hasInsertBlank = true;
                result[at++] = CharConstants.BLANK;
                i += (regLen - 1);
            } else {
                result[at++] = src.charAt(i);
                hasInsertBlank = false;
            }
        }
        if (at > 0 && CharConstants.BLANK == result[at - 1]) {
            at--;
        }
        if (at > 0) {
            return new String(result, 0, at);
        }
        return "";
    }

    /**
     * 移除{regex}字符
     * 
     * @param src
     * @return
     */
    public static String removeRegex(String src, String regex) {
        if (StringObject.isEmpty(src)) {
            return "";
        }
        if (StringObject.isEmpty(regex)) {
            return src;
        }
        int regLen = regex.length();
        char[] regChars = regex.toCharArray();
        char[] source = src.toCharArray();
        int length = src.length();
        char[] result = new char[length];
        int at = 0;
        for (int i = 0; i < length; i++) {
            if (regChars[0] == src.charAt(i) && isEquals(source, i, length, regChars, regLen)) {
                i += (regLen - 1);
            } else {
                result[at++] = src.charAt(i);
            }
        }
        if (at > 0) {
            return new String(result, 0, at);
        }
        return "";
    }

    public static boolean isEquals(char[] source, int from, int end, char[] target, int length) {
        if (end - from < length) {
            return false;
        }
        int i = 0;
        int at = from;
        for (i = 0; i < length && at < end; i++) {
            at = i + from;
            // System.out.println(source[at] + ", " + target[i]);
            if (target[i] != source[at]) {
                return false;
            }
        }
        // System.out.println(i + ", " + at);
        return (i == length);
    }
}
