package com.bytehonor.sdk.lang.spring.thread;

import java.util.concurrent.atomic.AtomicLong;

import com.bytehonor.sdk.lang.spring.string.SpringString;

public class ThreadIdGenerator {

    private static final String PREFIX = "SingleThread";

    private static final char SPL = '-';

    private final AtomicLong atomic = new AtomicLong(0);

    private ThreadIdGenerator() {
    }

    private String getId(String name) {
        StringBuilder sb = new StringBuilder();
        if (SpringString.isEmpty(name)) {
            name = PREFIX;
            sb.append(PREFIX);
        } else {
            sb.append(name);
        }
        return sb.append(SPL).append(atomic.incrementAndGet()).toString();
    }

    private static class LazyHolder {
        private static ThreadIdGenerator SINGLE = new ThreadIdGenerator();
    }

    private static ThreadIdGenerator self() {
        return LazyHolder.SINGLE;
    }

    public static String id(String name) {
        return self().getId(name);
    }
}
