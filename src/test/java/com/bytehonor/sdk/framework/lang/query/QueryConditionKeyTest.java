package com.bytehonor.sdk.framework.lang.query;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bytehonor.sdk.framework.lang.core.meta.Student;

public class QueryConditionKeyTest {

    @Test
    public void key_shouldBeStableAcrossRepeatedCalls() {
        String key1 = QueryCondition.key(Student::getCreateAt);
        String key2 = QueryCondition.key(Student::getCreateAt);

        assertEquals("create_at", key1);
        assertEquals(key1, key2);
    }
}
