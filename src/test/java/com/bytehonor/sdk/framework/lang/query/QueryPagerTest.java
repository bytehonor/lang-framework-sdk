package com.bytehonor.sdk.framework.lang.query;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bytehonor.sdk.framework.lang.constant.HttpConstants;

public class QueryPagerTest {

    @Test
    public void of_shouldNormalizeNegativeOffset() {
        QueryPager pager = QueryPager.of(-10, 5);

        assertEquals(HttpConstants.OFFSET_DEF, pager.getOffset());
    }

    @Test
    public void of_shouldKeepLimitNon() {
        QueryPager pager = QueryPager.of(0, HttpConstants.LIMIT_NON);

        assertEquals(HttpConstants.LIMIT_NON, pager.getLimit());
    }

    @Test
    public void of_shouldNormalizeInvalidLimitToDefault() {
        QueryPager pager = QueryPager.of(0, 0);

        assertEquals(HttpConstants.LIMIT_DEF, pager.getLimit());
    }
}
