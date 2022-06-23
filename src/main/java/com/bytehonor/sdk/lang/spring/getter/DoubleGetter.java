package com.bytehonor.sdk.lang.spring.getter;

import com.bytehonor.sdk.define.spring.exception.ParameterException;
import com.bytehonor.sdk.lang.spring.util.StringObject;

/**
 * @author lijianqiang
 *
 */
public class DoubleGetter {

    public static Double parse(String src) {
        try {
            return Double.valueOf(src);
        } catch (Exception e) {
            throw new ParameterException("not Double, src:" + src);
        }
    }

    public static Double optional(String src) {
        return optional(src, null);
    }

    public static Double optional(String src, Double def) {
        if (StringObject.isEmpty(src)) {
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
