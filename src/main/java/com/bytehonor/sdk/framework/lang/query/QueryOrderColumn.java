package com.bytehonor.sdk.framework.lang.query;

import java.util.Objects;

import com.bytehonor.sdk.framework.lang.constant.SqlOperator;

/**
 * 单条排序条件：列名与排序方向（ASC/DESC 等，取自 {@link SqlOperator}）。
 *
 * @author lijianqiang
 */
public class QueryOrderColumn {

    private String key;

    private String sorter;

    public QueryOrderColumn() {
        this("", "");
    }

    public QueryOrderColumn(String key, String sorter) {
        this.key = key;
        this.sorter = sorter;
    }

    public static QueryOrderColumn of(String key, String sorter) {
        Objects.requireNonNull(key, "key");
        return new QueryOrderColumn(key, sorter);
    }

    public static QueryOrderColumn desc(String key) {
        Objects.requireNonNull(key, "key");
        return of(key, SqlOperator.DESC.getOpt());
    }

    public static QueryOrderColumn asc(String key) {
        Objects.requireNonNull(key, "key");
        return of(key, SqlOperator.ASC.getOpt());
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSorter() {
        return sorter;
    }

    public void setSorter(String sorter) {
        this.sorter = sorter;
    }
}
