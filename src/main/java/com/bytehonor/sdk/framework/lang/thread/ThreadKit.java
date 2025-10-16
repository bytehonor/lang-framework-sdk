package com.bytehonor.sdk.framework.lang.thread;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.string.StringKit;

public final class ThreadKit {
    
    private static final Logger LOG = LoggerFactory.getLogger(ThreadKit.class);

    private static final String PREFIX = "UndefinedThread";

    private static final char SPL = '-';

    private final AtomicLong atomic = new AtomicLong(0);

    private ThreadKit() {
    }

    private String getId(String name) {
        StringBuilder sb = new StringBuilder();
        if (StringKit.isEmpty(name)) {
            name = PREFIX;
            sb.append(PREFIX);
        } else {
            sb.append(name);
        }
        return sb.append(SPL).append(atomic.incrementAndGet()).toString();
    }

    private static class LazyHolder {
        private static ThreadKit SINGLE = new ThreadKit();
    }

    private static ThreadKit self() {
        return LazyHolder.SINGLE;
    }

    public static String id(String name) {
        return self().getId(name);
    }
    
    public static void sleep(long millis) {
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

}
