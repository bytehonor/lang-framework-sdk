package com.bytehonor.sdk.beautify.lang.getter;

import com.bytehonor.sdk.beautify.lang.string.StringCreator;
import com.bytehonor.sdk.define.bytehonor.error.ParameterExcption;
import com.bytehonor.sdk.define.bytehonor.util.StringObject;

/**
 * @author lijianqiang
 *
 */
public class DoubleGetter {

    public static Double parse(String src) {
        try {
            return Double.valueOf(src);
        } catch (Exception e) {
            throw new ParameterExcption(StringCreator.create().append(src).append(" is not Double").toString());
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
