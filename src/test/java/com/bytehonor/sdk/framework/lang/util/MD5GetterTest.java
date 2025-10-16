package com.bytehonor.sdk.framework.lang.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5GetterTest {

    private static final Logger LOG = LoggerFactory.getLogger(MD5GetterTest.class);

    @Test
    public void test() {
        String text = "hello world, hello java!";
        String md5 = MD5Getter.md5(text);
        String target = "0862cb559f83edaeb04e455b413b2c1d";
        LOG.info("md5:{}", md5);

        assertTrue("testGetFull", target.equals(md5));
    }

}
