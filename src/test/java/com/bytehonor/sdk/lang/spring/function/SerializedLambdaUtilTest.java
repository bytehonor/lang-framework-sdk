package com.bytehonor.sdk.lang.spring.function;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerializedLambdaUtilTest {

    private static final Logger LOG = LoggerFactory.getLogger(SerializedLambdaUtilTest.class);

    @Test
    public void test() {
        String name = SerializedLambdaUtil.getFieldName(Student::getUpdateAt);
        LOG.info("test:{}", name);

        assertTrue("test", "updateAt".equals(name));
    }

    
    @Test
    public void test2() {
        String name = SerializedLambdaUtil.getFieldName(Student::isRich);
        LOG.info("test2:{}", name);

    }
}
