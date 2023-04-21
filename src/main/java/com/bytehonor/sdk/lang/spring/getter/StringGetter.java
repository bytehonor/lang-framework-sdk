package com.bytehonor.sdk.lang.spring.getter;

import java.util.Objects;

import com.bytehonor.sdk.lang.spring.string.SpringString;

/**
 * @author lijianqiang
 *
 */
public class StringGetter {

    public static String require(String src) {
        String val = optional(src, null);
        Objects.requireNonNull(val, "String");
        return val;
    }

    public static String optional(String src, String def) {
        return src != null ? src : def;
    }

    public static String getOrNull(String src) {
        return SpringString.isEmpty(src) ? null : src;
    }

}
