package com.bytehonor.sdk.framework.lang.constant;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SqlOperatorTestGte {

    @Test
    public void test() {
        assertTrue(SqlOperator.EGT == SqlOperator.keyOf("gte"));
        assertTrue(SqlOperator.ELT == SqlOperator.keyOf("lte"));
    }

}
