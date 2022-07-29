package com.bytehonor.sdk.lang.spring.function;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerializedLambdaUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(SerializedLambdaUtilsTest.class);

    @Test
    public void test() {
        String name = SerializedLambdaUtils.getFieldName(Student::getUpdateAt);
        LOG.info("test:{}", name);

        assertTrue("test", "updateAt".equals(name));
    }

    
    @Test
    public void test2() {
        String name = SerializedLambdaUtils.getFieldName(Student::isRich);
        LOG.info("test2:{}", name);

    }
}
