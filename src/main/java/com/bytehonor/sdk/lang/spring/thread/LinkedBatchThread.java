package com.bytehonor.sdk.lang.spring.thread;

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
     * @param name
     * @return
     */
    @Deprecated
    public static <T> LinkedBatchThread<T> create(QueueBatchConsumer<T> consumer, String name) {
        return create(consumer, name, INTERVAL_MILLIS);
    }

    /**
     * @param <T>
     * @param consumer
     * @param name
     * @param intervals
     * @return
     */
    @Deprecated
    public static <T> LinkedBatchThread<T> create(QueueBatchConsumer<T> consumer, String name, long intervals) {
        Objects.requireNonNull(consumer, "consumer");
        Objects.requireNonNull(name, "name");

        LinkedBatchThread<T> bt = new LinkedBatchThread<T>(consumer, intervals);
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

        private QueueBatchConsumer<T> consumer;

        private String name;

        private long intervals;

        private Builder() {
            this.intervals = INTERVAL_MILLIS;
            this.name = "unknown";
        }

        public Builder<T> consumer(QueueBatchConsumer<T> consumer) {
            Objects.requireNonNull(consumer, "consumer");
            this.consumer = consumer;
            return this;
        }

        public Builder<T> intervals(long intervals) {
            this.intervals = intervals;
            return this;
        }

        public Builder<T> name(Class<?> parent) {
            this.name = parent.getSimpleName();
            return this;
        }

        public LinkedBatchThread<T> build() {
            Objects.requireNonNull(consumer, "consumer");
            LinkedBatchThread<T> model = new LinkedBatchThread<T>(consumer, intervals);
            model.thread.setName(name);
            return model;
        }
    }
}
