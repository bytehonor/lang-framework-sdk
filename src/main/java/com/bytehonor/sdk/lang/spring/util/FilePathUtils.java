package com.bytehonor.sdk.lang.spring.util;

import java.util.Objects;

/**
 * 20211130
 * 
 * @author lijianqiang
 *
 */
public class FilePathUtils {

    private static final String SPL = "/";

    /**
     * 有/结尾
     * 
     * @param path1
     * @param path2
     * @return
     */
    public static String connectDirWithEnd(String path1, String path2) {
        Objects.requireNonNull(path1, "path1");
        String dir = connectPath(path1, path2);
        if (dir.endsWith(SPL) == false) {
            dir += SPL;
        }
        return dir;
    }

    /**
     * 简单连接
     * 
     * @param dir1
     * @param dir2
     * @return
     */
    public static String connectPath(String dir1, String dir2) {
        Objects.requireNonNull(dir1, "dir1");
        Objects.requireNonNull(dir2, "dir2");
        if (dir1.endsWith(SPL) == false) {
            dir1 += SPL;
        }
        if (dir2.startsWith(SPL)) {
            dir2 = dir2.substring(1);
        }
        return dir1 + dir2;
    }

    public static String subfixNoDot(String url) {
        String subfix = subfixWithDot(url);
        if (subfix == null || subfix.isEmpty()) {
            return subfix;
        }

        return subfix.substring(1, subfix.length());
    }

    public static String subfixWithDot(String url) {
        Objects.requireNonNull(url, "url");
        int at = url.indexOf('?');
        if (at > 1) {
            url = url.substring(0, at);
        }
        at = url.lastIndexOf('.');
        if (at < 0) {
            return "";
        }
        if (url.length() - at > 7) {
            return "";
        }
        return url.substring(at).toLowerCase();
    }
}
