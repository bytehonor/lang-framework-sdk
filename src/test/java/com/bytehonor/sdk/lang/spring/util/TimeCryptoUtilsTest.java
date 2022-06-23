package com.bytehonor.sdk.lang.spring.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeCryptoUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(TimeCryptoUtilsTest.class);

    @Test
    public void test() {
        int count = 0;
        int size = 10000;
        for (int i = 0; i < size; i++) {
            String secret = TimeCryptoUtils.encode();
            boolean isOk = TimeCryptoUtils.decode(secret) != null;
            LOG.info("{}, {}, {}", isOk, secret.length(), secret);
            if (isOk) {
                count++;
            }
        }
        assertTrue("*testMake32*", count == size);
    }

    @Test
    public void test2() {
        long time = System.currentTimeMillis();
        String hex = "";
        for (int i = 0; i < 200; i++) {
            time += i;
            hex = TimeCryptoUtils.toHex(time);
            LOG.info("time:{}, hex:{}, ok:{}", time, hex, time == TimeCryptoUtils.fromHex(hex));
        }
        assertTrue("*test2*", true);
    }
}
