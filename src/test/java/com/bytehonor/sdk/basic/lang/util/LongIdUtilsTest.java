package com.bytehonor.sdk.basic.lang.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LongIdUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(LongIdUtilsTest.class);

    private static long num = 9;

    private static String ENC = "cre66i9i";

    @Test
    public void testEncode() {
        String src = LongIdUtils.encode(num);
        LOG.info("src:{}", src);
        assertTrue("*testEncode*", ENC.equals(src));

    }

    @Test
    public void testDecode() {
        long val = LongIdUtils.decode(ENC);
        LOG.info("val:{}", val);
        assertTrue("*testDecode*", val == num);
    }

    @Test
    public void test() {
        long val = 1234567L;
        int size = 1000000;
        int success = 0;
        for (int i = 0; i < size; i++) {
            String src = LongIdUtils.encode(val);
            long n = LongIdUtils.decode(src);
            if (n == val) {
                success++;
            }
            val = val + i;
        }
        assertTrue("*testDecode*", size == success);
    }

}
