package com.bytehonor.sdk.framework.lang.meta;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.bytehonor.sdk.framework.lang.string.SpringString;

public class MetaGetter {

    private static final Map<String, String> UNDERLINE_CACHE = new ConcurrentHashMap<String, String>();

    public static String underline(String column) {
        Objects.requireNonNull(column, "column");

        String val = UNDERLINE_CACHE.get(column);
        if (SpringString.isEmpty(val) == false) {
            return val;
        }

        val = SpringString.camelToUnderline(column);
        UNDERLINE_CACHE.put(column, val);
        return val;
    }
}
