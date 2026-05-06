package com.bytehonor.sdk.framework.lang.query;

/**
 * 查询相关小工具（如生成“列名-操作符”唯一键）。
 *
 * @author lijianqiang
 */
public class QueryHelper {

    public static String unique(String key, String operator) {
        return new StringBuilder().append(key).append("-").append(operator).toString();
    }
}
