package com.bytehonor.sdk.basic.lang.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LongIdUtilsTest {

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
