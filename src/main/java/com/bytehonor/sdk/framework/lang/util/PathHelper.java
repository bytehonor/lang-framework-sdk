package com.bytehonor.sdk.framework.lang.util;

import java.util.Objects;

import com.bytehonor.sdk.framework.lang.string.SpringString;

/**
 * 20211130
 * 
 * @author lijianqiang
 *
 */
public class PathHelper {

    private static final String SPL = "/";

    /**
     * 有/结尾
     * 
     * @param path1
     * @param path2
     * @return
     */
    public static String connectWithEnd(String path1, String path2) {
        Objects.requireNonNull(path1, "path1");
        String path = connect(path1, path2);
        if (path.endsWith(SPL) == false) {
            path += SPL;
        }
        return path;
    }

    /**
     * 简单连接
     * 
     * @param path1
     * @param path2
     * @return
     */
    public static String connect(String path1, String path2) {
        Objects.requireNonNull(path1, "path1");
        Objects.requireNonNull(path2, "path2");

        StringBuilder sb = new StringBuilder();
        sb.append(path1);
        if (path1.endsWith(SPL) == false) {
            sb.append(SPL);
        }
        if (path2.startsWith(SPL)) {
            path2 = path2.substring(1);
        }
        sb.append(path2);
        return sb.toString();
    }

    public static String subfixNoDot(String path) {
        String subfix = subfixWithDot(path);
        if (SpringString.isEmpty(subfix)) {
            return "";
        }

        return subfix.substring(1, subfix.length());
    }

    public static String subfixWithDot(String path) {
        Objects.requireNonNull(path, "path");
        int at = path.indexOf('?');
        if (at > 1) {
            path = path.substring(0, at);
        }
        at = path.lastIndexOf('.');
        if (at < 0) {
            return "";
        }
        return path.substring(at).toLowerCase();
    }
}
