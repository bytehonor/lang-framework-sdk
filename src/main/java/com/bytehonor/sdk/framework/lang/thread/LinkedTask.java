package com.bytehonor.sdk.framework.lang.thread;

/**
 * 定时间隔链式任务：按固定间隔从 {@link QueueProducer} 取元素并由 {@link QueueConsumer} 消费。
 *
 * @author lijianqiang
 *
 */
public class LinkedTask<T> extends LoopIntervalTask {

    private final QueueProducer<T> producer;
    private final QueueConsumer<T> consumer;

    private final long intervals;

    /**
     * @param producer
     * @param consumer
     * @param intervals
     */
    public LinkedTask(QueueProducer<T> producer, QueueConsumer<T> consumer, long intervals) {
        this.producer = producer;
        this.consumer = consumer;
        this.intervals = intervals;
    }

    @Override
    public final void runThenSleep() {
        T payload = producer.produce();
        if (payload == null) {
            return;
        }
        consumer.consume(payload);
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
