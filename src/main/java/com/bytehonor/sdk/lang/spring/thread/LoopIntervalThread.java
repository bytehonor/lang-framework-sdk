package com.bytehonor.sdk.lang.spring.thread;

import java.util.Objects;

public class LoopIntervalThread implements ThreadParent {

    private final Thread thread;

    public LoopIntervalThread(String name, LoopIntervalTask task) {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(task, "task");
        this.thread = new Thread(task);
        this.thread.setName(name);
    }

    @Override
    public void start() {
        thread.start();
    }
}
