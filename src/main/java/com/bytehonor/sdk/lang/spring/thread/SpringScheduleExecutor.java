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

    private static final ScheduledExecutorService SERVICE = Executors.newSingleThreadScheduledExecutor();

    /**
     * 毫秒
     * 
     * @param command
     * @param delayMillis
     * @param periodMillis
     */
    public static void schedule(SafeTask command, long delayMillis, long periodMillis) {
        Objects.requireNonNull(command, "command");

        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        SERVICE.scheduleAtFixedRate(command, delayMillis, periodMillis, TimeUnit.MILLISECONDS);
    }

    /**
     * 秒
     * 
     * @param command
     * @param delaySeconds
     * @param periodSeconds
     */
    public static void scheduleSecond(SafeTask command, long delaySeconds, long periodSeconds) {
        Objects.requireNonNull(command, "command");

        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        SERVICE.scheduleAtFixedRate(command, delaySeconds, periodSeconds, TimeUnit.SECONDS);
    }
}
