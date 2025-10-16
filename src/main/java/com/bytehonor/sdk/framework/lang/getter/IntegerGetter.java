package com.bytehonor.sdk.framework.lang.getter;

import java.util.Objects;

import com.bytehonor.sdk.framework.lang.exception.SpringLangException;
import com.bytehonor.sdk.framework.lang.string.StringKit;

/**
 * @author lijianqiang
 *
 */
public class IntegerGetter {

    public static Integer require(String src) {
        Integer val = optional(src, null);
        Objects.requireNonNull(val, "Integer");
        return val;
    }

    public static Integer parse(String src) {
        try {
            return Integer.valueOf(src);
        } catch (Exception e) {
            throw new SpringLangException("not Integer, src:" + src);
        }
    }

    public static Integer optional(String src) {
        return optional(src, null);
    }

    public static Integer optional(String src, Integer def) {
        if (StringKit.isEmpty(src)) {
            return def;
        }
        try {
            return parse(src);
        } catch (Exception e) {
            return def;
        }
    }

    public static Integer optional(Integer src, Integer def) {
        return src != null ? src : def;
    }
}
