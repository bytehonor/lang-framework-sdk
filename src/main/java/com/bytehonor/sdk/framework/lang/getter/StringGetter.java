package com.bytehonor.sdk.framework.lang.getter;

import java.util.Objects;

import com.bytehonor.sdk.framework.lang.string.StringKit;

/**
 * 从字符串等原始输入安全获取非空 {@link String} 或带默认值的工具类。
 *
 * @author lijianqiang
 *
 */
public class StringGetter {

    public static String require(String src) {
        String val = optional(src, null);
        Objects.requireNonNull(val, "String");
        return val;
    }

    public static String optionals(String src) {
        return optional(src, "");
    }

    public static String optional(String src, String def) {
        return src != null ? src : def;
    }

    public static String getOrNull(String src) {
        return StringKit.isEmpty(src) ? null : src;
    }

}
