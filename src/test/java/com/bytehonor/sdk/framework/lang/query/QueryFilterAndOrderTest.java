package com.bytehonor.sdk.framework.lang.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class QueryFilterAndOrderTest {

    @Test
    public void getString_shouldFindByOperator() {
        QueryFilter filter = QueryFilter.plain()
                .with(QueryFilterColumn.eq("name", "alice"))
                .with(QueryFilterColumn.like("name", "ali"));

        assertEquals("alice", filter.getString("name", "eq"));
        assertEquals("ali", filter.getString("name", "like"));
        assertEquals("", filter.getString("name", "gt"));
    }

    @Test
    public void getString_shouldReturnEmptyForNullOrBlankOperator() {
        QueryFilter filter = QueryFilter.plain().with(QueryFilterColumn.eq("name", "alice"));

        assertEquals("", filter.getString("name", null));
        assertEquals("", filter.getString("name", ""));
    }

    @Test
    public void getString_shouldKeepFirstWhenDuplicateOperator() {
        QueryFilter filter = QueryFilter.plain()
                .with(QueryFilterColumn.eq("name", "first"))
                .with(QueryFilterColumn.eq("name", "second"));

        assertEquals("first", filter.getString("name", "eq"));
    }

    @Test
    public void with_shouldIgnoreNullColumn() {
        QueryOrder order = QueryOrder.plain().with(null);

        assertTrue(order.getColumns().isEmpty());
    }
}
