package com.bytehonor.sdk.framework.lang.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.framework.lang.string.StringKit;

/**
 * 查询过滤条件容器：维护已出现字段 key 集合与 {@link QueryFilterColumn} 列表。
 *
 * @author lijianqiang
 *
 */
public final class QueryFilter {

    private final Set<String> keys;

    private final List<QueryFilterColumn> columns;

    private final Map<String, QueryFilterColumn> uniqueColumns;

    private QueryFilter() {
        this.keys = new HashSet<String>();
        this.columns = new ArrayList<QueryFilterColumn>();
        this.uniqueColumns = new HashMap<String, QueryFilterColumn>();
    }

    public static QueryFilter plain() {
        return new QueryFilter();
    }

    public List<QueryFilterColumn> getColumns() {
        return columns;
    }

    public QueryFilter with(QueryFilterColumn column) {
        if (QueryFilterColumn.accept(column)) {
            this.keys.add(column.getKey());
            this.columns.add(column);
            this.uniqueColumns.putIfAbsent(column.unique(), column);
        }
        return this;
    }

    public boolean contains(String key) {
        if (StringKit.isEmpty(key)) {
            return false;
        }
        return this.keys.contains(key);
    }

    public boolean canFilter() {
        return !CollectionUtils.isEmpty(columns);
    }

    public String getString(String key, String operator) {
        if (!contains(key) || StringKit.isEmpty(operator)) {
            return "";
        }
        QueryFilterColumn column = uniqueColumns.get(QueryHelper.unique(key, operator));
        if (column == null || column.getValue() == null) {
            return "";
        }
        return Objects.toString(column.getValue(), "");
    }
}
