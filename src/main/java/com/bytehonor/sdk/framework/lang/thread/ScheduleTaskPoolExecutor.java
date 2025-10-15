package com.bytehonor.sdk.framework.lang.thread;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

/**
 * @author lijianqiang
 *
 */
public class ScheduleTaskPoolExecutor {

    private static final String NAMED = "schedule-task-thread-";

    private final ScheduledExecutorService service;

    private ScheduleTaskPoolExecutor() {
        int nThreads = Runtime.getRuntime().availableProcessors();
        this.service = Executors.newScheduledThreadPool(nThreads, new CustomizableThreadFactory(NAMED));
    }

    private static class LazyHolder {
        private static ScheduleTaskPoolExecutor SINGLE = new ScheduleTaskPoolExecutor();
    }

    private static ScheduleTaskPoolExecutor self() {
        return LazyHolder.SINGLE;
    }

    /**
     * scheduleAtFixedRate
     * 
     * @param task
     * @param delayMillis
     * @param intervalMillis
     */
    public static void schedule(SafeTask task, long delayMillis, long intervalMillis) {
        Objects.requireNonNull(task, "task");

        self().service.scheduleAtFixedRate(task, delayMillis, intervalMillis, TimeUnit.MILLISECONDS);
    }

    /**
     * schedule once
     * 
     * @param task
     * @param delayMillis
     */
    public static void schedule(SafeTask task, long delayMillis) {
        Objects.requireNonNull(task, "task");

        self().service.schedule(task, delayMillis, TimeUnit.MILLISECONDS);
    }
}
