package com.bytehonor.sdk.lang.bytehonor.getter;

import com.bytehonor.sdk.define.bytehonor.error.ParameterExcption;
import com.bytehonor.sdk.define.bytehonor.util.StringObject;
import com.bytehonor.sdk.lang.bytehonor.string.StringCreator;

/**
 * @author lijianqiang
 *
 */
public class IntegerGetter {
    
    public static Integer parse(String src) {
        try {
            return Integer.valueOf(src);
        } catch (Exception e) {
            throw new ParameterExcption(StringCreator.create().append(src).append(" is not Integer").toString());
        }
    }

    public static Integer optional(String src) {
        return optional(src, null);
    }

    public static Integer optional(String src, Integer def) {
        if (StringObject.isEmpty(src)) {
            return def;
        }
        try {
            return parse(src);
        } catch (Exception e) {
            return def;
        }
    }

    public static Integer optional(Integer src, Integer def) {
        return src != null ? src : def;
    }
}
