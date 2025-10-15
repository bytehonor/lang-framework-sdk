package com.bytehonor.sdk.framework.lang.query;

public class QueryHelper {

    public static String unique(String key, String operator) {
        return new StringBuilder().append(key).append("-").append(operator).toString();
    }
}
