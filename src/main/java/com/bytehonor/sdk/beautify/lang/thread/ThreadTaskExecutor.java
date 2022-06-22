package com.bytehonor.sdk.beautify.lang.thread;

import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadTaskExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(ThreadTaskExecutor.class);

    private final ThreadPoolExecutor executor;

    private ThreadTaskExecutor() {
        this.executor = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(4096));
    }

    /**
     * 延迟加载(线程安全)
     *
     */
    private static class LazyHolder {
        private static ThreadTaskExecutor SINGLE = new ThreadTaskExecutor();
    }

    private static ThreadTaskExecutor self() {
        return LazyHolder.SINGLE;
    }

    public static void put(Runnable r) {
        Objects.requireNonNull(r, "runnable");
        try {
            self().executor.execute(r);
        } catch (Exception e) {
            LOG.error("execute({}) error:{}", r.getClass().getSimpleName(), e);
        }
    }
}
