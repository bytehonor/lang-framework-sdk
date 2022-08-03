package com.bytehonor.sdk.lang.spring.util;

import java.util.Collection;

import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.lang.spring.constant.StringConstants;

public class JoinUtils {

    /**
     * with quote ''
     * 
     * @param list
     * @return
     */
    public static String joinSafe(Collection<?> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return StringConstants.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (Object val : collection) {
            sb.append("'").append(val).append("'").append(StringConstants.COM);
        }
        String str = sb.toString();
        return str.substring(0, str.length() - 1);
    }

    public static String join(Collection<?> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return StringConstants.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (Object val : collection) {
            sb.append(val).append(StringConstants.COM);
        }
        String str = sb.toString();
        return str.substring(0, str.length() - 1);
    }

    public static String longs(Collection<Long> collection) {
        return join(collection);
    }

    public static String integers(Collection<Integer> collection) {
        return join(collection);
    }

    public static String strings(Collection<String> collection) {
        return join(collection);
    }

    public static String stringsSafe(Collection<String> collection) {
        return joinSafe(collection);
    }
}
