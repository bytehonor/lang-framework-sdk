package com.bytehonor.sdk.lang.spring.thread;

import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 一次消费一个后阻塞
 * 
 * @author lijianqiang
 *
 * @param <T>
 */
public class LinkedBlockingThread<T> implements ThreadParent {

    private static final Logger LOG = LoggerFactory.getLogger(LinkedBlockingThread.class);

    private static final int QUEUE_SIZE = 4096;

    private final LinkedBlockingQueue<T> queue;

    private final Thread thread;

    private LinkedBlockingThread(int queues, QueueConsumer<T> consumer) {
        this.queue = new LinkedBlockingQueue<T>(queues);
        this.thread = new Thread(new LinkedBlockingTask<T>(new QueueBlockingProducer<T>() {

            @Override
            public T produce() throws InterruptedException {
                return take();
            }
        }, consumer));
    }

    private T take() throws InterruptedException {
        return this.queue.take();
    }

    /**
     * @param <T>
     * @param consumer
     * @return
     */
    public static <T> LinkedBlockingThread<T> create(QueueConsumer<T> consumer) {
        return create(QUEUE_SIZE, consumer);
    }

    /**
     * @param <T>
     * @param queues
     * @param consumer
     * @return
     */
    public static <T> LinkedBlockingThread<T> create(int queues, QueueConsumer<T> consumer) {
        Objects.requireNonNull(consumer, "consumer");

        return new LinkedBlockingThread<T>(queues, consumer);
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

    public LinkedBlockingThread<T> name(String name) {
        Objects.requireNonNull(name, "name");

        thread.setName(name);
        return this;
    }

    public LinkedBlockingThread<T> mount(Class<?> parent) {
        Objects.requireNonNull(parent, "parent");

        return name(parent.getSimpleName());
    }
}
