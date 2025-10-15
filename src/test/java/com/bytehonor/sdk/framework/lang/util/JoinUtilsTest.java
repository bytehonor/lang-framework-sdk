package com.bytehonor.sdk.framework.lang.util;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JoinUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(JoinUtilsTest.class);

    @Test
    public void testJoinStringSafe() {
        List<String> list = new ArrayList<String>();
        list.add("xxx");
        list.add("yyy");
        LOG.info("testJoinString:{}", JoinUtils.joinSafe(list));
        assertTrue("testJoinString", true);
    }

    @Test
    public void testJoinLong() {
        List<Long> list = new ArrayList<Long>();
        list.add(1L);
        list.add(2L);
        LOG.info("testJoinLong:{}", JoinUtils.longs(list));
        assertTrue("testJoinLong", true);
    }

    @Test
    public void testJoinInteger() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        LOG.info("testJoinInteger:{}", JoinUtils.integers(list));
        assertTrue("testJoinInteger", true);
    }

}
