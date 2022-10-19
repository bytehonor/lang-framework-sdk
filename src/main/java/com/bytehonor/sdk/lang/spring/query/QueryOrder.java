package com.bytehonor.sdk.lang.spring.query;

import java.util.Objects;

public class QueryOrder {

    private String key;

    private boolean desc;

    public static QueryOrder descOf(String key) {
        Objects.requireNonNull(key, "key");
        return new QueryOrder(key, true);
    }

    public static QueryOrder ascOf(String key) {
        Objects.requireNonNull(key, "key");
        return new QueryOrder(key, false);
    }

    public static QueryOrder of(String key, boolean desc) {
        Objects.requireNonNull(key, "key");
        return new QueryOrder(key, desc);
    }

    public static QueryOrder non() {
        return new QueryOrder();
    }

    public QueryOrder() {
        this("", false);
    }

    public QueryOrder(String key, boolean desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isDesc() {
        return desc;
    }

    public void setDesc(boolean desc) {
        this.desc = desc;
    }

    public QueryOrder desc(String key) {
        this.key = key;
        this.desc = true;
        return this;
    }

    public QueryOrder asc(String key) {
        this.key = key;
        this.desc = false;
        return this;
    }
}
