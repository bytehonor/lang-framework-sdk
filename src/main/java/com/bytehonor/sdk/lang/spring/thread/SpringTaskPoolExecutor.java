package com.bytehonor.sdk.lang.spring.thread;

import java.util.Objects;
import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lijianqiang
 *
 */
public class SpringTaskPoolExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(SpringTaskPoolExecutor.class);

    private static final String NAMED = "spring-task-thread-";

    private final ExecutorService service;

    private SpringTaskPoolExecutor() {
        this.service = ThreadPoolBuilder.full(NAMED);
    }

    /**
     * 延迟加载(线程安全)
     *
     */
    private static class LazyHolder {
        private static SpringTaskPoolExecutor SINGLE = new SpringTaskPoolExecutor();
    }

    private static SpringTaskPoolExecutor self() {
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
