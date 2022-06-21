package com.bytehonor.sdk.lang.bytehonor.task;

import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AsyncTaskExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(AsyncTaskExecutor.class);

    private ThreadPoolExecutor executor;

    private AsyncTaskExecutor() {
        this.executor = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(4096));
    }

    /**
     * 延迟加载(线程安全)
     *
     */
    private static class LazyHolder {
        private static AsyncTaskExecutor instance = new AsyncTaskExecutor();
    }

    public static void restart(int corePoolSize, int maxPoolSize, int queueSize) {
        shutdown();
        self().executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(queueSize));
    }

    private static AsyncTaskExecutor self() {
        return LazyHolder.instance;
    }

    public static void execute(Runnable r) {
        Objects.requireNonNull(r, "runnable");
        try {
            self().executor.execute(r);
        } catch (Exception e) {
            LOG.error("execute({}) error:{}", r.getClass().getSimpleName(), e);
        }
    }

    public static void shutdown() {
        boolean shutdown = self().executor.isShutdown();
        LOG.info("shutdown:{}", shutdown);
        if (!shutdown) {
            self().executor.shutdown();
        }
    }

    public static int queueSize() {
        return self().executor.getQueue().size();
    }
}
