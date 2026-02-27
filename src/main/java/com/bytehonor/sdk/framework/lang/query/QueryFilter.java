package com.bytehonor.sdk.framework.lang.query;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.util.CollectionUtils;

/**
 * @author lijianqiang
 *
 */
public final class QueryFilter {

    private final Set<String> keys;

    private final List<QueryFilterColumn> columns;

    private QueryFilter() {
        this.keys = new HashSet<String>();
        this.columns = new ArrayList<QueryFilterColumn>();
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
        }
        return this;
    }

    public boolean contains(String key) {
        return this.keys.contains(key);
    }

    public boolean canFilter() {
        return CollectionUtils.isEmpty(columns) == false;
    }

    public String getString(String key, String operator) {
        if (contains(key) == false) {
            return "";
        }

        String value = "";
        for (QueryFilterColumn column : columns) {
            if (Objects.equals(key, column.getKey()) && Objects.equals(operator, column.getOperator().getKey())) {
                value = column.getValue().toString();
                break;
            }
        }
        return value;
    }
}
