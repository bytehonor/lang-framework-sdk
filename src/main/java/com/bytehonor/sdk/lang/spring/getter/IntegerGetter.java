package com.bytehonor.sdk.lang.spring.getter;

import com.bytehonor.sdk.define.spring.exception.ParameterException;
import com.bytehonor.sdk.lang.spring.util.StringObject;

/**
 * @author lijianqiang
 *
 */
public class IntegerGetter {

    public static Integer parse(String src) {
        try {
            return Integer.valueOf(src);
        } catch (Exception e) {
            throw new ParameterException("not Integer, src:" + src);
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
