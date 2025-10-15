package com.bytehonor.sdk.framework.lang.thread;

import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 一次消费多个后休眠
 * 
 * @author lijianqiang
 *
 * @param <T>
 */
public class LinkedBatchThread<T> implements ThreadParent {

    private static final Logger LOG = LoggerFactory.getLogger(LinkedBatchThread.class);

    private static final long INTERVAL_MILLIS = 1000L;

    private final ConcurrentLinkedQueue<T> queue;

    private final Thread thread;

    private LinkedBatchThread(QueueBatchConsumer<T> consumer, long intervals) {
        this.queue = new ConcurrentLinkedQueue<T>();
        this.thread = new Thread(new LinkedBatchTask<T>(new QueueProducer<T>() {

            @Override
            public T produce() {
                return poll();
            }
        }, consumer, intervals));
    }

    private T poll() {
        return this.queue.poll();
    }

    /**
     * @param <T>
     * @param consumer
     * @return
     */
    public static <T> LinkedBatchThread<T> create(QueueBatchConsumer<T> consumer) {
        return create(consumer, INTERVAL_MILLIS);
    }

    /**
     * @param <T>
     * @param intervals
     * @param consumer
     * @return
     */
    public static <T> LinkedBatchThread<T> create(QueueBatchConsumer<T> consumer, long intervals) {
        Objects.requireNonNull(consumer, "consumer");

        return new LinkedBatchThread<T>(consumer, intervals);
    }

    @Override
    public void start() {
        this.thread.start();
        LOG.info("[{}] start", thread.getName());
    }

    public void add(T payload) {
        if (payload == null) {
            LOG.warn("add payload null");
            return;
        }
        this.queue.add(payload);
    }

    public LinkedBatchThread<T> name(String name) {
        Objects.requireNonNull(name, "name");

        thread.setName(name);
        return this;
    }

    public LinkedBatchThread<T> mount(Class<?> parent) {
        Objects.requireNonNull(parent, "parent");

        return name(parent.getSimpleName());
    }
}
