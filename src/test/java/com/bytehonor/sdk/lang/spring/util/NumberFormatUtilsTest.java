package com.bytehonor.sdk.lang.spring.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberFormatUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(NumberFormatUtilsTest.class);

    @Test
    public void testDot2String() {
        String dot2 = NumberFormatUtils.format("123456789.987654321");
        LOG.info("testDot2String :{}", dot2);
    }

    @Test
    public void testDot2Double() {
        String dot2 = NumberFormatUtils.format(123456789.987654321);
        LOG.info("testDot2Double :{}", dot2);
    }

    @Test
    public void testDot2Long() {
        String dot2 = NumberFormatUtils.format(123456789L);
        LOG.info("testDot2Long :{}", dot2);
    }

    @Test
    public void testDot2Integer() {
        String dot2 = NumberFormatUtils.format(123456789);
        LOG.info("testDot2Integer :{}", dot2);
    }

}
