package com.bytehonor.sdk.framework.lang.getter;

import java.util.Objects;

import com.bytehonor.sdk.framework.lang.string.StringKit;

/**
 * @author lijianqiang
 *
 */
public class BooleanGetter {

    private static final String TRUE = "true";

    private static final String YES = "yes";

    public static Boolean require(String src) {
        Boolean val = optional(src, null);
        Objects.requireNonNull(val, "Boolean");
        return val;
    }

    public static Boolean optional(String src) {
        return optional(src, null);
    }

    public static Boolean optional(String src, Boolean defVal) {
        if (StringKit.isEmpty(src)) {
            return defVal;
        }
        String lc = src.toLowerCase();
        return TRUE.equals(lc) || YES.equals(lc);
    }

    public static Boolean optional(Boolean src, Boolean def) {
        return src != null ? src : def;
    }

}
