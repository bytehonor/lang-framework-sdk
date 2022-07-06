package com.bytehonor.sdk.lang.spring.thread;

import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpringTaskExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(SpringTaskExecutor.class);

    private final ThreadPoolExecutor executor;

    private SpringTaskExecutor() {
        this.executor = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(4096));
    }

    /**
     * 延迟加载(线程安全)
     *
     */
    private static class LazyHolder {
        private static SpringTaskExecutor SINGLE = new SpringTaskExecutor();
    }

    private static SpringTaskExecutor self() {
        return LazyHolder.SINGLE;
    }

    public static void put(Runnable runnable) {
        Objects.requireNonNull(runnable, "runnable");
        try {
            self().executor.execute(runnable);
        } catch (Exception e) {
            LOG.error("execute({}) error:{}", runnable.getClass().getSimpleName(), e);
        }
    }
}
