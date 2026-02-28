package com.bytehonor.sdk.framework.lang.core.field;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.function.Student;

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
        String name = SerializedLambdaUtils.getFieldName(Student::isVeryRich);
        LOG.info("test2:{}", name);

        assertTrue("test2", "veryRich".equals(name));
    }

    @Test
    public void test3() {
        int size = 10000000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            SerializedLambdaUtils.getFieldName(Student::getUpdateAt);
        }
        LOG.info("test3 1 cost:{}", System.currentTimeMillis() - start); // 128ms

        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            SerializedLambdaUtil.getFieldName(Student::getUpdateAt);
        }
        LOG.info("test3 2 cost:{}", System.currentTimeMillis() - start); // 8173ms
    }
}
