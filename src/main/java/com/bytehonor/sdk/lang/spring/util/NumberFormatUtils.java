package com.bytehonor.sdk.lang.spring.util;

import java.text.DecimalFormat;

import com.bytehonor.sdk.lang.spring.getter.DoubleGetter;
import com.bytehonor.sdk.lang.spring.string.SpringString;

public class NumberFormatUtils {

    private static final DecimalFormat DF = new DecimalFormat(",###.##");

    private static final String ZERO = "0.0";

    private static final String YI = "亿";

    private static final String WANG = "万";

    private static final long WANG_VAL = 10000L;

    private static final long YI_VAL = 100000000L;

    public static String cn(Long val) {
        if (val == null) {
            return "未知";
        }

        // 万以下不处理
        if (val < WANG_VAL) {
            return String.valueOf(val);
        }
        Double dou = 0.0;
        String unit = "";
        if (val < YI_VAL) {
            dou = DoubleMathUtils.divide(Double.valueOf(val), 10000.0, 2);
            unit = WANG;
        } else {
            dou = DoubleMathUtils.divide(Double.valueOf(val), 100000000.0, 2);
            unit = YI;
        }
        String src = String.valueOf(dou);
        if (src.endsWith(".0")) {
            src = src.substring(0, src.length() - 2);
        }
        return new StringBuilder().append(src).append(unit).toString();
    }

    public static String cn(Integer val) {
        if (val == null) {
            return "未知";
        }

        return cn((long) val);
    }

    public static String en(String val) {
        if (SpringString.isEmpty(val)) {
            return ZERO;
        }
        Double dou = DoubleGetter.optional(val);
        if (dou == null) {
            return ZERO;
        }
        return DF.format(dou);
    }

    public static String en(Double val) {
        if (val == null) {
            return ZERO;
        }
        return DF.format(val);
    }

    public static String en(Long val) {
        if (val == null) {
            return ZERO;
        }
        return DF.format(val);
    }

    public static String en(Integer val) {
        if (val == null) {
            return ZERO;
        }
        return DF.format(val);
    }
}
