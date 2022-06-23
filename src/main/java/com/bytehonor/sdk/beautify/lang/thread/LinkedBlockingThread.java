package com.bytehonor.sdk.beautify.lang.thread;

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
public class LinkedBlockingThread<T> {

    private static final Logger LOG = LoggerFactory.getLogger(LinkedBlockingThread.class);

    private static final int QUEUE_SIZE = 1024;

    private final LinkedBlockingQueue<T> queue;

    private final Thread thread;

    private LinkedBlockingThread(QueueConsumer<T> consumer, int queueSize) {
        this.queue = new LinkedBlockingQueue<T>(queueSize);
        this.thread = new Thread(new LinkedBlockingTask<T>(new QueueBlockingProducer<T>() {

            @Override
            public T produce() throws InterruptedException {
                return take();
            }
        }, consumer));
    }

    /**
     * @param <T>
     * @param consumer
     * @param name
     * @return
     */
    public static <T> LinkedBlockingThread<T> create(QueueConsumer<T> consumer, String name) {
        return create(consumer, name, QUEUE_SIZE);
    }

    /**
     * @param <T>
     * @param consumer
     * @param name
     * @param queueSize
     * @return
     */
    public static <T> LinkedBlockingThread<T> create(QueueConsumer<T> consumer, String name, int queueSize) {
        Objects.requireNonNull(consumer, "consumer");
        Objects.requireNonNull(name, "name");

        LinkedBlockingThread<T> bt = new LinkedBlockingThread<T>(consumer, queueSize);
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

    public T take() throws InterruptedException {
        return this.queue.take();
    }
}
