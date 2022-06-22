package com.bytehonor.sdk.lang.bytehonor.thread;

public class LinkedBlockingTask<T> extends WhileBlockRunner {

    private final QueueBlockingProducer<T> producer;
    private final QueueConsumer<T> consumer;

    public LinkedBlockingTask(QueueBlockingProducer<T> producer, QueueConsumer<T> consumer) {
        this.producer = producer;
        this.consumer = consumer;
    }

    @Override
    public final void runThenBlock() throws InterruptedException {
        T payload = producer.produce();
        consumer.consume(payload);
    }
}
