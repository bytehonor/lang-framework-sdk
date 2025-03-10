package com.bytehonor.sdk.lang.spring.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.lang.spring.constant.SqlOperator;

public class QueryOrder {

    private final List<QueryOrderColumn> columns;

    public QueryOrder() {
        this.columns = new ArrayList<QueryOrderColumn>();
    }

    public static QueryOrder non() {
        return new QueryOrder();
    }

    public QueryOrder desc(String key) {
        Objects.requireNonNull(key, "key");
        return sort(key, SqlOperator.DESC.getOpt());
    }

    public QueryOrder asc(String key) {
        Objects.requireNonNull(key, "key");
        return sort(key, SqlOperator.ASC.getOpt());
    }

    public QueryOrder sort(String key, String sorter) {
        this.columns.add(QueryOrderColumn.of(key, sorter));
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
