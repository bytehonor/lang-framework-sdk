package com.bytehonor.sdk.lang.spring.query;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.bytehonor.sdk.lang.spring.meta.Student;

public class QueryConditionTest {

    @Test
    public void test() {
        QueryCondition condition = QueryCondition.and().eq(Student::getCreateAt, 0L).gt(Student::getAge, 1);

        assertTrue("*testTime*", condition.has(Student::getCreateAt));
    }
}
