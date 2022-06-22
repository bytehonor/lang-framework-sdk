package com.bytehonor.sdk.lang.bytehonor.thread;

public class LinkedTask<T> extends WhileSleepRunner {

    private final QueueProducer<T> producer;
    private final QueueConsumer<T> consumer;

    private final long millis;

    public LinkedTask(QueueProducer<T> producer, QueueConsumer<T> consumer, long millis) {
        this.producer = producer;
        this.consumer = consumer;
        this.millis = millis;
    }

    @Override
    public final void runThenSleep() {
        T payload = producer.produce();
        consumer.consume(payload);
    }

    @Override
    public long millis() {
        return millis;
    }

}
