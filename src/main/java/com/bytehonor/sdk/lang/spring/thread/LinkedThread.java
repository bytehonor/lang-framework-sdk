package com.bytehonor.sdk.lang.spring.thread;

import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 一次消费一个后休眠
 * 
 * @author lijianqiang
 *
 * @param <T>
 */
public class LinkedThread<T> {

    private static final Logger LOG = LoggerFactory.getLogger(LinkedThread.class);

    private static final long INTERVAL_MILLIS = 1000L;

    private final ConcurrentLinkedQueue<T> queue;

    private final Thread thread;

    private LinkedThread(QueueConsumer<T> consumer, long intervals) {
        this.queue = new ConcurrentLinkedQueue<T>();
        this.thread = new Thread(new LinkedTask<T>(new QueueProducer<T>() {

            @Override
            public T produce() {
                return poll();
            }
        }, consumer, intervals));
    }

    /**
     * @param <T>
     * @param consumer
     * @param name
     * @return
     */
    public static <T> LinkedThread<T> create(QueueConsumer<T> consumer, String name) {
        return create(consumer, name, INTERVAL_MILLIS);
    }

    /**
     * @param <T>
     * @param consumer
     * @param name
     * @param intervals
     * @return
     */
    public static <T> LinkedThread<T> create(QueueConsumer<T> consumer, String name, long intervals) {
        Objects.requireNonNull(consumer, "consumer");
        Objects.requireNonNull(name, "name");

        LinkedThread<T> bt = new LinkedThread<T>(consumer, intervals);
        bt.thread.setName(name);
        return bt;
    }

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

    public T poll() {
        return this.queue.poll();
    }
}
