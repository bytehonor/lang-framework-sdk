package com.bytehonor.sdk.lang.spring.util;

import java.util.Set;

public class SetJoinUtils {

    private static final String CON = ",";

    /**
     * with quote ''
     * 
     * @param set
     * @return
     */
    public static String joinStringSafe(Set<String> set) {
        if (set == null || set.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String val : set) {
            sb.append("'").append(val).append("',");
        }
        String str = sb.toString();
        return str.substring(0, str.length() - 1);
    }

    public static String joinString(Set<String> set) {
        if (set == null || set.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String val : set) {
            sb.append(val).append(",");
        }
        String str = sb.toString();
        return str.substring(0, str.length() - 1);
    }

    public static String joinLong(Set<Long> set) {
        if (set == null || set.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Long val : set) {
            sb.append(val).append(CON);
        }
        String str = sb.toString();
        return str.substring(0, str.length() - 1);
    }

    public static String joinInteger(Set<Integer> set) {
        if (set == null || set.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Integer val : set) {
            sb.append(val).append(CON);
        }
        String str = sb.toString();
        return str.substring(0, str.length() - 1);
    }

}
