package com.bytehonor.sdk.framework.lang.thread;

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
public class LinkedThread<T> implements ThreadParent {

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

    private T poll() {
        return this.queue.poll();
    }

    /**
     * @param <T>
     * @param consumer
     * @return
     */
    public static <T> LinkedThread<T> create(QueueConsumer<T> consumer) {
        return create(consumer, INTERVAL_MILLIS);
    }

    /**
     * @param <T>
     * @param consumer
     * @param intervals
     * @return
     */
    public static <T> LinkedThread<T> create(QueueConsumer<T> consumer, long intervals) {
        Objects.requireNonNull(consumer, "consumer");

        return new LinkedThread<T>(consumer, intervals);
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

    public LinkedThread<T> mount(Class<?> parent) {
        Objects.requireNonNull(parent, "parent");

        thread.setName(parent.getSimpleName());
        return this;
    }
}
