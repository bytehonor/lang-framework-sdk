package com.bytehonor.sdk.lang.spring.thread;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

/**
 * @author lijianqiang
 *
 */
public class RateLimitedExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(RateLimitedExecutor.class);

    private static final String NAMED = "rate-limited-thread-";

    private final ExecutorService service;

    private RateLimitedExecutor() {
        this.service = Executors.newFixedThreadPool(1, new CustomizableThreadFactory(NAMED));
    }

    private static class LazyHolder {
        private static RateLimitedExecutor SINGLE = new RateLimitedExecutor();
    }

    private static RateLimitedExecutor self() {
        return LazyHolder.SINGLE;
    }

    private static class RateLimitTask<T> extends SafeTask {

        private final long intervals;
        private final T model;
        private final Consumer<T> consumer;

        public RateLimitTask(long intervals, T model, Consumer<T> consumer) {
            this.intervals = intervals;
            this.model = model;
            this.consumer = consumer;
        }

        @Override
        public void runInSafe() {
            apply(intervals, model, consumer);
        }
    }

    public static <T> void add(long intervals, T model, Consumer<T> consumer) {
        Objects.requireNonNull(model, "model");
        Objects.requireNonNull(consumer, "consumer");

        self().service.execute(new RateLimitTask<T>(intervals, model, consumer));
    }

    public static <T> void apply(long intervals, T model, Consumer<T> consumer) {
        Objects.requireNonNull(model, "model");
        Objects.requireNonNull(consumer, "consumer");

        long begin = System.currentTimeMillis();
        try {
            consumer.accept(model);
        } catch (Exception e) {
            LOG.error("type:{} error", model.getClass().getSimpleName(), e);
        }
        sleep(begin, intervals);
    }

    private static void sleep(long begin, long intervals) {
        long millis = begin + intervals - System.currentTimeMillis();
        Sleep.millis(millis);
    }
}
