package com.bytehonor.sdk.lang.spring.thread;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

/**
 * @author lijianqiang
 *
 */
public final class ThreadPoolBuilder {

    public static ExecutorService build(int nThreads, String name) {
        Objects.requireNonNull(name, "name");

        return Executors.newFixedThreadPool(nThreads, new CustomizableThreadFactory(name));
    }

    public static ExecutorService single(String name) {
        return build(1, name);
    }

    public static ExecutorService half(String name) {
        int nThreads = Runtime.getRuntime().availableProcessors();
        if (nThreads > 1) {
            nThreads = nThreads / 2;
        }
        return build(nThreads, name);
    }

    public static ExecutorService full(String name) {
        int nThreads = Runtime.getRuntime().availableProcessors();
        return build(nThreads, name);
    }
}
