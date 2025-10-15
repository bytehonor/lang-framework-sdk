package com.bytehonor.sdk.framework.lang.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.framework.lang.constant.SqlOperator;
import com.bytehonor.sdk.framework.lang.string.SpringString;

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
        if (SpringString.isEmpty(column.getKey()) || SpringString.isEmpty(column.getSorter())) {
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

    public static final class QueryOrderColumn {

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

}
