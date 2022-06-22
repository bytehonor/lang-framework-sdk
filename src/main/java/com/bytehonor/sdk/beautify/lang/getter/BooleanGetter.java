package com.bytehonor.sdk.beautify.lang.getter;

import java.util.Set;

import com.bytehonor.sdk.define.bytehonor.util.StringObject;
import com.google.common.collect.Sets;

/**
 * @author lijianqiang
 *
 */
public class BooleanGetter {

    private static final Set<String> YES = Sets.newHashSet("true", "yes");

    public static Boolean optional(String src) {
        return optional(src, null);
    }

    public static Boolean optional(String src, Boolean defVal) {
        if (StringObject.isEmpty(src)) {
            return defVal;
        }
        return YES.contains(src.toLowerCase());
    }

    public static Boolean optional(Boolean src, Boolean def) {
        return src != null ? src : def;
    }

}
