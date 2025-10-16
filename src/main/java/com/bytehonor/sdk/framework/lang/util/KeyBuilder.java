package com.bytehonor.sdk.framework.lang.util;

import java.util.Objects;

public class KeyBuilder {

    private static final String SPL = "-";

    private static final String REDIS = ":";

    public static String plain(Object... args) {
        Objects.requireNonNull(args, "args");

        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Object arg : args) {
            if (i != 0) {
                sb.append(SPL);
            }
            sb.append(arg);
            i++;
        }
        return sb.toString();
    }

    public static String md5(Object... args) {
        return MD5Getter.md5(plain(args));
    }

    public static String cache(Object... args) {
        Objects.requireNonNull(args, "args");

        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Object arg : args) {
            if (i != 0) {
                sb.append(REDIS);
            }
            sb.append(arg);
            i++;
        }
        return sb.toString();
    }
}
