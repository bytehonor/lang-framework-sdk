package com.bytehonor.sdk.framework.lang.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.junit.Test;

import com.bytehonor.sdk.framework.lang.core.meta.Student;

public class QueryConditionBehaviorTest {

    @Test
    public void in_shouldIgnoreEmptyCollection() {
        QueryCondition condition = QueryCondition.and().in(Student::getNickname, Collections.emptyList());

        assertFalse(condition.canFilter());
    }

    @Test
    public void descIfNon_shouldOnlyApplyFirstOrder() {
        QueryCondition condition = QueryCondition.and()
                .descIfNon(Student::getCreateAt)
                .ascIfNon(Student::getNickname);

        assertTrue(condition.canOrder());
        assertEquals(1, condition.getOrder().getColumns().size());
    }
}
