package com.bytehonor.sdk.beautify.lang.string;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.define.bytehonor.util.StringObject;

/**
 * <pre>
 * 字符串提取
 * </pre>
 * 
 * @author lijianqiang
 *
 */
public class StringExtractUtils {

    private static Logger LOG = LoggerFactory.getLogger(StringExtractUtils.class);

    public static final char IGNORE_CHAR = '*';

    public static String extract(String src, String beginner, String ender) {
        Objects.requireNonNull(src, "src");
        if (StringObject.isEmpty(src) || StringObject.isEmpty(beginner) || StringObject.isEmpty(ender)) {
            return src;
        }
        final int length = src.length();
        final char[] source = src.toCharArray();
        final int beginLength = beginner.length();
        final char[] beginChar = beginner.toCharArray();
        final int endLenght = ender.length();
        final char[] endChar = ender.toCharArray();

        boolean findBegin = false;
        int beginAt = 0;
        int i = 0;
        int j = 0;
        int k = 0;
        for (i = 0; i < length; i++) {
            if (source[i] != beginChar[0]) {
                continue;
            }
            for (j = 0, k = i + j; j < beginLength && k < length; j++, k++) {
                if (IGNORE_CHAR == beginChar[j]) {
                    continue;
                }
                if (source[k] != beginChar[j]) {
                    break;
                }
            }
            if (j == beginLength) {
                findBegin = true;
                beginAt = i;
                break;
            }
        }

        LOG.debug("findBegin:{}, beginAt:{}, i:{}", findBegin, beginAt, i);
        if (findBegin == false) {
            return "";
        }

        boolean findEnd = false;
        int count = 0;
        for (; i < length; i++) {
            count++;
            if (source[i] != endChar[0]) {
                continue;
            }
            for (j = 0, k = i + j; j < endLenght && k < length; j++, k++) {
                if (IGNORE_CHAR == endChar[j]) {
                    continue;
                }
                if (source[k] != endChar[j]) {
                    break;
                }
            }
            if (j == endLenght) {
                count += (endLenght - 1);
                findEnd = true;
                break;
            }
        }
        if (findBegin && findEnd) {
            // LOG.debug("beginAt:{}, count:{}", beginAt, count);
            return new String(source, beginAt, count);
        }
        return "";
    }
}
