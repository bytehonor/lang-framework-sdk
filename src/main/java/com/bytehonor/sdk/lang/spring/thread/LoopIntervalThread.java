package com.bytehonor.sdk.lang.spring.thread;

import java.util.Objects;

public class LoopIntervalThread {

    private final Thread thread;

    public LoopIntervalThread(String name, LoopIntervalTask task) {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(task, "task");
        this.thread = new Thread(task);
        this.thread.setName(name);
    }

    public void start() {
        thread.start();
    }
}
