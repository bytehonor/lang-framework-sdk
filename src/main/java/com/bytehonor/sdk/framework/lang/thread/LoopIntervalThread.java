package com.bytehonor.sdk.framework.lang.thread;

import java.util.Objects;

/**
 * 将 {@link LoopIntervalTask} 包装为具名 {@link Thread} 并对外提供 {@link #start()}。
 *
 * @author lijianqiang
 */
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
