package com.bytehonor.sdk.lang.bytehonor.getter;

/**
 * @author lijianqiang
 *
 */
public class StringGetter {

    public static String optional(String src, String def) {
        return src != null ? src : def;
    }

}
