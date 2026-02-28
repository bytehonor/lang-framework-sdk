package com.bytehonor.sdk.framework.lang.core.field;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.bytehonor.sdk.framework.lang.string.StringKit;

public class FieldNameKit {

    private static final Map<String, String> UNDERLINE_CACHE = new ConcurrentHashMap<String, String>();

    public static String underline(String column) {
        Objects.requireNonNull(column, "column");

        String val = UNDERLINE_CACHE.get(column);
        if (StringKit.isEmpty(val) == false) {
            return val;
        }

        val = StringKit.camelToUnderline(column);
        UNDERLINE_CACHE.put(column, val);
        return val;
    }
}
