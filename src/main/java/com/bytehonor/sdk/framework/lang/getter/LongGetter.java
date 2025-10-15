package com.bytehonor.sdk.framework.lang.getter;

import java.util.Objects;

import com.bytehonor.sdk.framework.lang.exception.SpringLangException;
import com.bytehonor.sdk.framework.lang.string.SpringString;

/**
 * @author lijianqiang
 *
 */
public class LongGetter {
    
    public static Long require(String src) {
        Long val = optional(src, null);
        Objects.requireNonNull(val, "Long");
        return val;
    }

    public static Long parse(String src) {
        try {
            return Long.valueOf(src);
        } catch (Exception e) {
            throw new SpringLangException("not Long, src:" + src);
        }
    }

    public static Long optional(String src) {
        return optional(src, null);
    }

    public static Long optional(String src, Long def) {
        if (SpringString.isEmpty(src)) {
            return def;
        }
        try {
            return parse(src);
        } catch (Exception e) {
            return def;
        }
    }

    public static Long optional(Long val, Long def) {
        return val != null ? val : def;
    }
}
