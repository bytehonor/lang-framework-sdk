package com.bytehonor.sdk.lang.spring.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileHelperTest {

    private static final Logger LOG = LoggerFactory.getLogger(FileHelperTest.class);

    @Test
    public void testConnectDirWhitEnd() {
        String rootDir = "/dir/";
        String sub1 = "name";
        String sub2 = "name";
        String sub3 = "/name";
        String sub4 = "/name/";
        String target = "/dir/name/";
        boolean res1 = target.equals(FileHelper.connectWithEnd(rootDir, sub1));
        boolean res2 = target.equals(FileHelper.connectWithEnd(rootDir, sub2));
        boolean res3 = target.equals(FileHelper.connectWithEnd(rootDir, sub3));
        boolean res4 = target.equals(FileHelper.connectWithEnd(rootDir, sub4));
        boolean res = res1 && res2 && res3 && res4;
        assertTrue("*testConnectDirWhitEnd*", res);
    }

    @Test
    public void testConnectPath() {
        String rootDir = "/dir/";
        String sub1 = "name";
        String sub2 = "name";
        String sub3 = "/name";
        String target = "/dir/name";
        boolean res1 = target.equals(FileHelper.connect(rootDir, sub1));
        boolean res2 = target.equals(FileHelper.connect(rootDir, sub2));
        boolean res3 = target.equals(FileHelper.connect(rootDir, sub3));

        LOG.info("{}, {}, {}", res1, res2, res3);
        boolean res = res1 && res2 && res3;
        assertTrue("*testConnectPath*", res);
    }
}
