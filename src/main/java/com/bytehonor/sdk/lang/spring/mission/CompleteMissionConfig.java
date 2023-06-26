package com.bytehonor.sdk.lang.spring.mission;

public class CompleteMissionConfig {

    private static final long TASK_DELAY_MILLIS = 1000L;

    private static final long TASK_INTERVAL_MILLIS = 1000L * 60;

    private static final long TASK_LOCK_MILLIS = 1000L * 100;

    private long taskDelayMillis;

    private long taskIntervalMillis;

    private long taskLockMillis;

    public CompleteMissionConfig() {
        this.taskDelayMillis = TASK_DELAY_MILLIS;
        this.taskIntervalMillis = TASK_INTERVAL_MILLIS;
        this.taskLockMillis = TASK_LOCK_MILLIS;
    }

    public long getTaskDelayMillis() {
        return taskDelayMillis;
    }

    public void setTaskDelayMillis(long taskDelayMillis) {
        this.taskDelayMillis = taskDelayMillis;
    }

    public long getTaskIntervalMillis() {
        return taskIntervalMillis;
    }

    public void setTaskIntervalMillis(long taskIntervalMillis) {
        this.taskIntervalMillis = taskIntervalMillis;
    }

    public long getTaskLockMillis() {
        return taskLockMillis;
    }

    public void setTaskLockMillis(long taskLockMillis) {
        this.taskLockMillis = taskLockMillis;
    }

}
