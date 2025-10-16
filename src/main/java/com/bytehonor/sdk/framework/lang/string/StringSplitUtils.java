package com.bytehonor.sdk.framework.lang.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bytehonor.sdk.framework.lang.constant.CharConstants;

public class StringSplitUtils {

    private static final char SPL = ',';

    private static final char SPL_CN = '，';

    /**
     * 逗号分隔，中英逗号均支持
     * 
     * @param src
     * @return
     */
    public static List<String> split(String src) {
        if (StringKit.isEmpty(src)) {
            return new ArrayList<String>();
        }
        boolean replaced = false;
        int length = src.length();
        char[] chars = src.toCharArray();
        char[] targets = new char[length * 2];
        for (int i = 0; i < length; i++) {
            if (chars[i] == SPL || chars[i] == SPL_CN) {
                targets[i] = SPL;
                replaced = true;
            } else {
                targets[i] = chars[i];
            }
        }

        if (replaced) {
            src = new String(targets, 0, length);
        }

        return split(src, SPL);
    }

    public static List<String> splitWithBlank(String src) {
        return split(src, CharConstants.BLANK);
    }

    public static List<String> split(String src, char sp) {
        if (StringKit.isEmpty(src)) {
            return new ArrayList<String>();
        }
        int length = src.length();
        List<String> res = new ArrayList<String>(length * 2);
        int begin = 0;
        int count = 0;
        boolean findOne = false;
        boolean beginNewOne = true;
        char[] charArray = src.toCharArray();
        for (int i = 0; i < length; i++) {
            if (sp != charArray[i]) {
                if (beginNewOne) {
                    begin = i;
                    beginNewOne = false;
                }
                count++;
                findOne = false;
            } else {
                findOne = true;
            }

            if (findOne && count > 0) {
                res.add(new String(charArray, begin, count));
                count = 0;
                beginNewOne = true;
            }
        }

        if (count > 0) {
            res.add(new String(charArray, begin, count));
        }

        return res;
    }

    public static Set<String> toSet(String src) {
        return toSet(src, SPL);
    }

    public static Set<String> toSet(String src, char sp) {
        if (StringKit.isEmpty(src)) {
            return new HashSet<String>();
        }
        int length = src.length();
        Set<String> res = new HashSet<String>(length * 2);
        int begin = 0;
        int count = 0;
        boolean findOne = false;
        boolean beginNewOne = true;
        char[] charArray = src.toCharArray();
        for (int i = 0; i < length; i++) {
            if (sp != charArray[i]) {
                if (beginNewOne) {
                    begin = i;
                    beginNewOne = false;
                }
                count++;
                findOne = false;
            } else {
                findOne = true;
            }

            if (findOne && count > 0) {
                res.add(new String(charArray, begin, count));
                count = 0;
                beginNewOne = true;
            }
        }

        if (count > 0) {
            res.add(new String(charArray, begin, count));
        }

        return res;
    }
}
