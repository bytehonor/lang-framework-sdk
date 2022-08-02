package com.bytehonor.sdk.lang.spring.getter;

import com.bytehonor.sdk.lang.spring.string.StringObject;

/**
 * @author lijianqiang
 *
 */
public class BooleanGetter {

    private static final String TRUE = "true";

    private static final String YES = "yes";

    public static Boolean optional(String src) {
        return optional(src, null);
    }

    public static Boolean optional(String src, Boolean defVal) {
        if (StringObject.isEmpty(src)) {
            return defVal;
        }
        String lc = src.toLowerCase();
        return TRUE.equals(lc) || YES.equals(lc);
    }

    public static Boolean optional(Boolean src, Boolean def) {
        return src != null ? src : def;
    }

}
