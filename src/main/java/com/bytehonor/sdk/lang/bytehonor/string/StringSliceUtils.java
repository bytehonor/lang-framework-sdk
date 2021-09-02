package com.bytehonor.sdk.lang.bytehonor.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bytehonor.sdk.define.bytehonor.util.StringObject;

/**
 * <pre>
 * 字符串切割
 * </pre>
 * 
 * @author lijianqiang
 *
 */
public class StringSliceUtils {

    /**
     * slice切割成长度为1和2的字符串集合
     * 
     * @param text
     * @return
     */
    public static Set<String> slice(String text) {
        return slice(text, 2);
    }

    /**
     * slice切割成长度为1,2,3的字符串集合
     * 
     * @param text
     * @return
     */
    public static Set<String> slice3(String text) {
        return slice(text, 3);
    }

    /**
     * slice切割成长度为limit以内的字符串集合
     * 
     * @param text
     * @param limit
     * @return
     */
    public static Set<String> slice(String text, int limit) {
        Set<String> set = new HashSet<String>();
        if (StringObject.isEmpty(text)) {
            return set;
        }
        int len = text.length();
        char[] src = text.toCharArray();
        for (int i = 0; i < len; i++) {
            set.add(new String(src, i, 1));
            // 2位以上的
            for (int j = 2; j <= limit; j++) {
                int begin = j - 1;
                if (i - begin >= 0) {
                    set.add(new String(src, i - begin, j));
                }
            }
        }
        return set;
    }

    /**
     * slice切割句子,长度为2字符串顺序数组
     * 
     * @param text
     * @return
     */
    public static List<String> sliceOrderd(String text) {
        return sliceOrderd(text, 2);
    }

    /**
     * slice切割句子,长度为limit字符串顺序数组
     * 
     * @param text
     * @param limit
     * @return
     */
    public static List<String> sliceOrderd(String text, int limit) {
        List<String> list = new ArrayList<String>();
        if (StringObject.isEmpty(text)) {
            return list;
        }
        int len = text.length();
        char[] src = text.toCharArray();
        for (int i = 0; i < len; i++) {
            list.add(new String(src, i, 1));
            // 2位以上的
            for (int j = 2; j <= limit; j++) {
                int begin = j - 1;
                if (i - begin >= 0) {
                    list.add(new String(src, i - begin, j));
                }
            }
        }
        return list;
    }
}
