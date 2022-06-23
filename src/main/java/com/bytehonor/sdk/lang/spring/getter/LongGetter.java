package com.bytehonor.sdk.lang.spring.getter;

import com.bytehonor.sdk.define.spring.exception.ParameterException;
import com.bytehonor.sdk.lang.spring.util.StringObject;

/**
 * @author lijianqiang
 *
 */
public class LongGetter {

    public static Long parse(String src) {
        try {
            return Long.valueOf(src);
        } catch (Exception e) {
            throw new ParameterException("not Long, src:" + src);
        }
    }

    public static Long optional(String src) {
        return optional(src, null);
    }

    public static Long optional(String src, Long def) {
        if (StringObject.isEmpty(src)) {
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
