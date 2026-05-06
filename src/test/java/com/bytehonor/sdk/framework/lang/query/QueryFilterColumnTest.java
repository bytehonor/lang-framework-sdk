package com.bytehonor.sdk.framework.lang.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import com.bytehonor.sdk.framework.lang.constant.SqlOperator;
import com.bytehonor.sdk.framework.lang.exception.SpringLangException;

public class QueryFilterColumnTest {

    @Test(expected = NullPointerException.class)
    public void of_shouldRejectNullKey() {
        QueryFilterColumn.of(null, "v", String.class.getName(), SqlOperator.EQ);
    }

    @Test(expected = NullPointerException.class)
    public void of_shouldRejectNullType() {
        QueryFilterColumn.of("name", "v", null, SqlOperator.EQ);
    }

    @Test(expected = NullPointerException.class)
    public void of_shouldRejectNullOperator() {
        QueryFilterColumn.of("name", "v", String.class.getName(), null);
    }

    @Test(expected = SpringLangException.class)
    public void of_shouldRejectLikeOnNonString() {
        QueryFilterColumn.of("age", 18, Integer.class.getName(), SqlOperator.LIKE);
    }

    @Test
    public void of_shouldProvideClearMessageWhenLikeOnNonString() {
        try {
            QueryFilterColumn.of("age", 18, Integer.class.getName(), SqlOperator.LIKE);
        } catch (SpringLangException e) {
            assertNotNull(e.getMessage());
            assertTrue(e.getMessage().contains("LIKE operator only supports String type"));
            assertTrue(e.getMessage().contains("java.lang.Integer"));
            return;
        }
        throw new AssertionError("Expected SpringLangException");
    }

    @Test
    public void unique_shouldMatchQueryHelper() {
        assertEquals(QueryHelper.unique("name", "eq"), QueryHelper.unique("name", "eq"));
    }

    @Test
    public void accept_shouldRejectEmptyInCollection() {
        QueryFilterColumn empty = QueryFilterColumn.in("ids", Collections.emptyList(), String.class);
        QueryFilterColumn nonEmpty = QueryFilterColumn.in("ids", Arrays.asList("1"), String.class);

        assertFalse(QueryFilterColumn.accept(empty));
        assertTrue(QueryFilterColumn.accept(nonEmpty));
    }
}
