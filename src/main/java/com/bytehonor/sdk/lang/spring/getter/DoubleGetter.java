package com.bytehonor.sdk.lang.spring.getter;

import com.bytehonor.sdk.lang.spring.exception.SpringLangException;
import com.bytehonor.sdk.lang.spring.string.SpringString;

/**
 * @author lijianqiang
 *
 */
public class DoubleGetter {

    public static Double parse(String src) {
        try {
            return Double.valueOf(src);
        } catch (Exception e) {
            throw new SpringLangException("not Double, src:" + src);
        }
    }

    public static Double optional(String src) {
        return optional(src, null);
    }

    public static Double optional(String src, Double def) {
        if (SpringString.isEmpty(src)) {
            return def;
        }
        try {
            return parse(src);
        } catch (Exception e) {
            return def;
        }
    }

    public static Double optional(Double val, Double def) {
        return val != null ? val : def;
    }
}
