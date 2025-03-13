package com.bytehonor.sdk.lang.spring.query;

public class QueryHelper {

    public static String unique(String key, String operator) {
        return new StringBuilder().append(key).append("-").append(operator).toString();
    }
}
