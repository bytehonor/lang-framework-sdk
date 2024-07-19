package com.bytehonor.sdk.lang.spring.thread;

import java.util.Objects;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RateLimitedExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(RateLimitedExecutor.class);

    public static <T> void apply(long intervals, T model, Consumer<T> consumer) {
        Objects.requireNonNull(model, "model");

        long begin = System.currentTimeMillis();
        try {
            consumer.accept(model);
        } catch (Exception e) {
            LOG.error("type:{} error", model.getClass().getSimpleName(), e);
        }
        sleep(begin, intervals);
    }

    public static void sleep(long begin, long intervals) {
        long millis = begin + intervals - System.currentTimeMillis();
        Sleep.millis(millis);
    }
}
