package com.bytehonor.sdk.lang.spring.util;

import java.text.DecimalFormat;

import com.bytehonor.sdk.lang.spring.getter.DoubleGetter;

public class NumberFormatUtils {

    private static final DecimalFormat DF = new DecimalFormat(",###.##");

    private static final String ZERO = "0.0";

    public static String format(String val) {
        if (StringObject.isEmpty(val)) {
            return ZERO;
        }
        Double dou = DoubleGetter.optional(val);
        if (dou == null) {
            return ZERO;
        }
        return DF.format(dou);
    }

    public static String format(Double val) {
        if (val == null) {
            return ZERO;
        }
        return DF.format(val);
    }

    public static String format(Long val) {
        if (val == null) {
            return ZERO;
        }
        return DF.format(val);
    }

    public static String format(Integer val) {
        if (val == null) {
            return ZERO;
        }
        return DF.format(val);
    }
}
