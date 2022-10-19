package com.bytehonor.sdk.lang.spring.query;

import com.bytehonor.sdk.lang.spring.constant.HttpConstants;

public class QueryPager {

    private boolean counted;

    private int offset;

    private int limit;

    public QueryPager() {
        this.counted = false;
        this.offset = 0;
        this.limit = HttpConstants.LIMIT_DEF;
    }

    public static QueryPager def() {
        return new QueryPager();
    }

    public static QueryPager of(int offset, int limit) {
        return of(false, offset, limit);
    }

    public static QueryPager of(boolean counted, int offset, int limit) {
        QueryPager model = new QueryPager();
        model.setCounted(counted);
        model.setOffset(offset > -1 ? offset : HttpConstants.OFFSET_DEF);
        model.setLimit(limit);
        return model;
    }

    public boolean isCounted() {
        return counted;
    }

    public void setCounted(boolean counted) {
        this.counted = counted;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

}
