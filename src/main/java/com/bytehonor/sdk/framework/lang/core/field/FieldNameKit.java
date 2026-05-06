package com.bytehonor.sdk.framework.lang.core.field;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.bytehonor.sdk.framework.lang.string.StringKit;

/**
 * 驼峰字段名转下划线列名，带进程内缓存。
 *
 * @author lijianqiang
 */
public class FieldNameKit {

    private static final Map<String, String> UNDERLINE_CACHE = new ConcurrentHashMap<String, String>();

    /**
     * 将驼峰形式 {@code column} 转为下划线形式；相同入参多次调用命中缓存。
     *
     * @param column 非空字段名或列名片段
     * @return 下划线风格字符串
     */
    public static String underline(String column) {
        Objects.requireNonNull(column, "column");

        String val = UNDERLINE_CACHE.get(column);
        if (StringKit.isEmpty(val) == false) {
            return val;
        }

        val = StringKit.camelToUnderline(column);
        UNDERLINE_CACHE.put(column, val);
        return val;
    }
}
