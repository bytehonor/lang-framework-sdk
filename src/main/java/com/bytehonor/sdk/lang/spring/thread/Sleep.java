package com.bytehonor.sdk.lang.spring.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.core.Randomizer;

/**
 * @author lijianqiang
 *
 */
public class Sleep {

    private static final Logger LOG = LoggerFactory.getLogger(Sleep.class);

    public static void millis(long millis) {
        if (millis < 1L) {
            return;
        }

        LOG.debug("sleep millis:{}", millis);

        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            LOG.error("sleep", e);
        }
    }

    /**
     * 
     * @param min
     * @param max
     */
    public static void rand(int min, int max) {
        int rand = Randomizer.integer(min, max);
        millis(100L * rand);
    }
}
