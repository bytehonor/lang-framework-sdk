package com.bytehonor.sdk.framework.lang.util;

import java.util.Objects;

/**
 * 将多个片段拼接为业务主键（普通 {@code -} 分隔、Redis 风格 {@code :} 分隔或先拼接再 MD5）。
 *
 * @author lijianqiang
 */
public class KeyBuilder {

    private static final String SPL = "-";

    private static final String REDIS = ":";

    /**
     * 使用 {@code '-'} 连接各参数 {@link String#valueOf(Object)}。
     */
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

    /**
     * 对 {@link #plain(Object...)} 的结果计算 MD5 十六进制摘要。
     */
    public static String md5(Object... args) {
        return MD5Kit.md5(plain(args));
    }

    /**
     * 使用 {@code ':'} 连接各参数，常用于缓存或 Redis key 拼装。
     */
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
