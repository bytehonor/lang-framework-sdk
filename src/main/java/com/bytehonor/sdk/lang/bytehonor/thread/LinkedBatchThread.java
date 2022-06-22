package com.bytehonor.sdk.lang.bytehonor.thread;

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
public class LinkedBatchThread<T> {

    private static final Logger LOG = LoggerFactory.getLogger(LinkedBatchThread.class);

    private final ConcurrentLinkedQueue<T> queue;

    private final Thread thread;

    private LinkedBatchThread(QueueBatchConsumer<T> consumer, long millis) {
        this.queue = new ConcurrentLinkedQueue<T>();
        this.thread = new Thread(new LinkedBatchTask<T>(new QueueProducer<T>() {

            @Override
            public T produce() {
                return poll();
            }
        }, consumer, millis));
    }

    public static <T> LinkedBatchThread<T> create(QueueBatchConsumer<T> consumer, String name) {
        return create(consumer, name, 200L);
    }

    public static <T> LinkedBatchThread<T> create(QueueBatchConsumer<T> consumer, String name, long millis) {
        Objects.requireNonNull(consumer, "consumer");
        Objects.requireNonNull(name, "name");

        LinkedBatchThread<T> bt = new LinkedBatchThread<T>(consumer, millis);
        bt.thread.setName(name);
        return bt;
    }

    public void start() {
        this.thread.start();
        LOG.info("[{}] start", thread.getName());
    }

    public void add(T payload) {
        if (payload == null) {
            LOG.warn("add null");
            return;
        }
        this.queue.add(payload);
    }

    public T poll() {
        return this.queue.poll();
    }
}
