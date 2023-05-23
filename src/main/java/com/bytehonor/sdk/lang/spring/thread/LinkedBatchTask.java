package com.bytehonor.sdk.lang.spring.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 一次消费多个
 * 
 * @author lijianqiang
 *
 * @param <T>
 */
public class LinkedBatchTask<T> extends LoopIntervalTask {

    private final QueueProducer<T> producer;
    private final QueueBatchConsumer<T> consumer;

    private final long intervals;

    /**
     * @param producer
     * @param consumer
     * @param intervals
     */
    public LinkedBatchTask(QueueProducer<T> producer, QueueBatchConsumer<T> consumer, long intervals) {
        this.producer = producer;
        this.consumer = consumer;
        this.intervals = intervals;
    }

    @Override
    public final void runThenSleep() {
        int size = consumer.size();
        List<T> payloads = new ArrayList<T>(size * 2);
        for (int i = 0; i < size; i++) {
            T payload = producer.produce();
            if (payload == null) {
                break;
            }
            payloads.add(payload);
        }

        if (payloads.isEmpty()) {
            return;
        }

        consumer.consume(payloads);
    }

    @Override
    public long intervals() {
        return intervals;
    }

    @Override
    public long delays() {
        return 0;
    }

}
