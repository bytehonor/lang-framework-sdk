package com.bytehonor.sdk.framework.lang.constant;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlOperatorTest {

    private static final Logger LOG = LoggerFactory.getLogger(SqlOperatorTest.class);

    @Test
    public void test() {
        for (SqlOperator item : SqlOperator.values()) {
            LOG.info("key:{}, name:{}, to:{}", item.key(), item.name(), item);
        }

    }

}
