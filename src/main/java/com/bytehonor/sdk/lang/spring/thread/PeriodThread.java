package com.bytehonor.sdk.lang.spring.thread;

import java.util.Objects;

public class PeriodThread {

    private final Thread thread;

    public PeriodThread(String name, WhileSleepTask task) {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(task, "task");
        this.thread = new Thread(task);
        this.thread.setName(name);
    }

    public void start() {
        thread.start();
    }
}
