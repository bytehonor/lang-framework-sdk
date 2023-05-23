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
public class LinkedBlockingThread<T> {

    private static final Logger LOG = LoggerFactory.getLogger(LinkedBlockingThread.class);

    private static final int QUEUE_SIZE = 1024;

    private final LinkedBlockingQueue<T> queue;

    private final Thread thread;

    private LinkedBlockingThread(QueueConsumer<T> consumer, int queues) {
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
     * @param name
     * @return
     */
    @Deprecated
    public static <T> LinkedBlockingThread<T> create(QueueConsumer<T> consumer, String name) {
        return create(consumer, name, QUEUE_SIZE);
    }

    /**
     * @param <T>
     * @param consumer
     * @param name
     * @param queues
     * @return
     */
    @Deprecated
    public static <T> LinkedBlockingThread<T> create(QueueConsumer<T> consumer, String name, int queues) {
        Objects.requireNonNull(consumer, "consumer");
        Objects.requireNonNull(name, "name");

        LinkedBlockingThread<T> bt = new LinkedBlockingThread<T>(consumer, queues);
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

    public static <T> Builder<T> builder(Class<T> clz) {
        return new Builder<T>();
    }

    public static class Builder<T> {

        private QueueConsumer<T> consumer;

        private String name;

        private int queues;

        private Builder() {
            this.queues = QUEUE_SIZE;
            this.name = "unknown";
        }

        public Builder<T> consumer(QueueConsumer<T> consumer) {
            Objects.requireNonNull(consumer, "consumer");
            this.consumer = consumer;
            return this;
        }

        public Builder<T> queues(int queues) {
            this.queues = queues;
            return this;
        }

        public Builder<T> name(Class<?> parent) {
            this.name = parent.getSimpleName();
            return this;
        }

        public LinkedBlockingThread<T> build() {
            Objects.requireNonNull(consumer, "consumer");
            LinkedBlockingThread<T> model = new LinkedBlockingThread<T>(consumer, queues);
            model.thread.setName(name);
            return model;
        }
    }
}
