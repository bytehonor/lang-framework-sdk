package com.bytehonor.sdk.framework.lang.string;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bytehonor.sdk.framework.lang.constant.CharConstants;

/**
 * 按分隔符拆分字符串为列表（支持中英文逗号归一、空白分隔等）。
 *
 * @author lijianqiang
 */
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
            return new ArrayList<>();
        }
        return split(normalizeComma(src), SPL);
    }

    /**
     * 按空白字符拆分 {@code src}。
     */
    public static List<String> splitWithBlank(String src) {
        return split(src, CharConstants.BLANK);
    }

    /**
     * 按指定字符 {@code sp} 拆分 {@code src}，连续分隔符之间不产生空串片段。
     */
    public static List<String> split(String src, char sp) {
        return splitToCollection(src, sp, new ArrayList<>());
    }

    public static Set<String> toSet(String src) {
        return toSet(src, SPL);
    }

    public static Set<String> toSet(String src, char sp) {
        return splitToCollection(src, sp, new HashSet<>());
    }

    private static String normalizeComma(String src) {
        if (src.indexOf(SPL_CN) < 0) {
            return src;
        }
        char[] chars = src.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == SPL_CN) {
                chars[i] = SPL;
            }
        }
        return new String(chars);
    }

    private static <C extends Collection<String>> C splitToCollection(String src, char sp, C target) {
        if (StringKit.isEmpty(src)) {
            return target;
        }
        int length = src.length();
        int tokenStart = -1;
        for (int i = 0; i < length; i++) {
            if (src.charAt(i) == sp) {
                if (tokenStart >= 0) {
                    target.add(src.substring(tokenStart, i));
                    tokenStart = -1;
                }
            } else if (tokenStart < 0) {
                tokenStart = i;
            }
        }
        if (tokenStart >= 0) {
            target.add(src.substring(tokenStart, length));
        }
        return target;
    }
}
