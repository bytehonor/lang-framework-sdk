package com.bytehonor.sdk.lang.bytehonor.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.define.bytehonor.util.RandomUtils;

public class SleepUtils {

    private static final Logger LOG = LoggerFactory.getLogger(SleepUtils.class);

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            LOG.error("sleep", e);
        }
    }

    public static void rand(int min, int max) {
        int rand = RandomUtils.getInt(min, max);
        sleep(100L * rand);
    }
}
