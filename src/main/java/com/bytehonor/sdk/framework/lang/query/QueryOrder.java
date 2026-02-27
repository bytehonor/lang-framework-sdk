package com.bytehonor.sdk.framework.lang.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.framework.lang.string.StringKit;

public final class QueryOrder {

    private final List<QueryOrderColumn> columns;

    public QueryOrder() {
        this.columns = new ArrayList<QueryOrderColumn>();
    }

    public static QueryOrder plain() {
        return new QueryOrder();
    }

    public QueryOrder desc(String key) {
        Objects.requireNonNull(key, "key");
        return with(QueryOrderColumn.desc(key));
    }

    public QueryOrder asc(String key) {
        Objects.requireNonNull(key, "key");
        return with(QueryOrderColumn.asc(key));
    }

    public QueryOrder with(QueryOrderColumn column) {
        if (StringKit.isEmpty(column.getKey()) || StringKit.isEmpty(column.getSorter())) {
            return this;
        }
        this.columns.add(column);
        return this;
    }

    public List<QueryOrderColumn> getColumns() {
        return columns;
    }

    public boolean canOrder() {
        return CollectionUtils.isEmpty(columns) == false;
    }

}
