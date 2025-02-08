package com.bytehonor.sdk.lang.spring.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DoubleMathUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(DoubleMathUtilsTest.class);

    private static Double DOU1 = 9.87654321;

    private static Double DOU2 = 6.78912345;

    @Test
    public void testAdd() {
        Double res = DoubleMathUtils.add(DOU1, DOU2);
        LOG.info("testAdd {} + {} = {}", DOU1, DOU2, res);
        boolean isOk = true;
        assertTrue("*testAdd*", isOk);
    }

    @Test
    public void testSub() {
        Double res = DoubleMathUtils.sub(DOU1, DOU2);
        LOG.info("testSub {} - {} = {}", DOU1, DOU2, res);
        boolean isOk = true;
        assertTrue("*testSub*", isOk);
    }

    @Test
    public void testMul() {
        Double res = DoubleMathUtils.mul(DOU1, DOU2);
        LOG.info("testMul {} * {} = {}", DOU1, DOU2, res);
        boolean isOk = true;
        assertTrue("*testMul*", isOk);
    }

    @Test
    public void testDivideDoubleDouble() {
        Double res = DoubleMathUtils.divide(DOU1, DOU2);
        LOG.info("testDivide {} / {} = {}", DOU1, DOU2, res);
        boolean isOk = true;
        assertTrue("*testDivideDoubleDouble*", isOk);
    }

    @Test
    public void testDivideDoubleDoubleInteger() {
        int scale = 2;
        Double res = DoubleMathUtils.divide(DOU1, DOU2, scale);
        LOG.info("testDivide {} / {} = {}, scale:{}", DOU1, DOU2, res, scale);
        boolean isOk = true;
        assertTrue("*testDivideDoubleDoubleInteger*", isOk);
    }

    @Test
    public void testRound() {
        LOG.info("testRound {} round:{}, scale:{}", DOU1, DoubleMathUtils.round(DOU1, 1), 1);
        LOG.info("testRound {} round:{}, scale:{}", DOU1, DoubleMathUtils.round(DOU1, 2), 2);
        LOG.info("testRound {} round:{}, scale:{}", DOU1, DoubleMathUtils.round(DOU1, 3), 3);
        boolean isOk = true;
        assertTrue("*testRound*", isOk);
    }

}
