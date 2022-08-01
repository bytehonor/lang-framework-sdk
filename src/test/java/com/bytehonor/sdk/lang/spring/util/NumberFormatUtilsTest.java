package com.bytehonor.sdk.lang.spring.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberFormatUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(NumberFormatUtilsTest.class);

    @Test
    public void testEnString() {
        String en = NumberFormatUtils.en("123456789.987654321");
        LOG.info("testEnString :{}", en);
    }

    @Test
    public void testEnDouble() {
        String en = NumberFormatUtils.en(123456789.987654321);
        LOG.info("testEnDouble :{}", en);
    }

    @Test
    public void testEnLong() {
        String en = NumberFormatUtils.en(123456789L);
        LOG.info("testEnLong :{}", en);
    }

    @Test
    public void testEnInteger() {
        String en = NumberFormatUtils.en(123456789);
        LOG.info("testEnInteger :{}", en);
    }

    @Test
    public void testCn() {
        long small = 100L;
        long middle = 240L;
        long big = 999L;
        for (int i = 0; i < 9; i++) {
            String s = NumberFormatUtils.cn(small);
            String m = NumberFormatUtils.cn(middle);
            String b = NumberFormatUtils.cn(big);
            LOG.info("{}={}, {}={}, {}={}", small, s, middle, m, big, b);
            small *= 10;
            middle *= 10;
            big *= 10;
        }
    }

}
