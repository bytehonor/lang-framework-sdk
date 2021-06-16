package com.bytehonor.sdk.lang.bytehonor.util;

import java.util.Set;

public class SetJoinUtils {

    private static final String CON = ",";

    /**
     * with quote ''
     * 
     * @param list
     * @return
     */
    public static String joinStringSafe(Set<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String val : list) {
            sb.append("'").append(val).append("',");
        }
        String str = sb.toString();
        return str.substring(0, str.length() - 1);
    }

    public static String joinString(Set<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String val : list) {
            sb.append(val).append(",");
        }
        String str = sb.toString();
        return str.substring(0, str.length() - 1);
    }

    public static String joinLong(Set<Long> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Long val : list) {
            sb.append(val).append(CON);
        }
        String str = sb.toString();
        return str.substring(0, str.length() - 1);
    }

    public static String joinInteger(Set<Integer> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Integer val : list) {
            sb.append(val).append(CON);
        }
        String str = sb.toString();
        return str.substring(0, str.length() - 1);
    }

}
