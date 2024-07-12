package com.bytehonor.sdk.lang.spring.thread;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author lijianqiang
 *
 */
public class SpringScheduleExecutor {

    private final ScheduledExecutorService service;

    private SpringScheduleExecutor() {
        this.service = Executors.newSingleThreadScheduledExecutor();
    }

    private static class LazyHolder {
        private static SpringScheduleExecutor SINGLE = new SpringScheduleExecutor();
    }

    private static SpringScheduleExecutor self() {
        return LazyHolder.SINGLE;
    }

    private void scheduleAtFixedRate(SafeTask task, long delayMillis, long intervalMillis) {
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(task, delayMillis, intervalMillis, TimeUnit.MILLISECONDS);
    }

    /**
     * 毫秒
     * 
     * @param task
     * @param delayMillis
     * @param intervalMillis
     */
    public static void scheduleMillis(SafeTask task, long delayMillis, long intervalMillis) {
        Objects.requireNonNull(task, "task");

        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        self().scheduleAtFixedRate(task, delayMillis, intervalMillis);
    }

    /**
     * 秒
     * 
     * @param command
     * @param delaySeconds
     * @param intervalSeconds
     */
    @Deprecated
    public static void schedule(SafeTask command, long delaySeconds, long intervalSeconds) {
        Objects.requireNonNull(command, "command");

        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        self().service.scheduleAtFixedRate(command, delaySeconds, intervalSeconds, TimeUnit.SECONDS);
    }
}
