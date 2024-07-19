package com.bytehonor.sdk.lang.spring.thread;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

public class SpringTaskExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(SpringTaskExecutor.class);

    private static final String NAMED = "spring-task-thread-";

    private final ExecutorService service;

    private SpringTaskExecutor() {
        int nThreads = Runtime.getRuntime().availableProcessors();
        this.service = Executors.newFixedThreadPool(nThreads + 1, new CustomizableThreadFactory(NAMED));
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
            self().service.execute(runnable);
        } catch (Exception e) {
            LOG.error("execute({}) error:{}", runnable.getClass().getSimpleName(), e);
        }
    }
}
