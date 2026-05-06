package com.bytehonor.sdk.framework.lang.thread;

/**
 * 阻塞链式任务：每次从 {@link QueueBlockingProducer} 取出一个元素并交给 {@link QueueConsumer} 处理。
 *
 * @author lijianqiang
 *
 */
public class LinkedBlockingTask<T> extends LoopBlockingTask {

    private final QueueBlockingProducer<T> producer;
    private final QueueConsumer<T> consumer;

    /**
     * @param producer
     * @param consumer
     */
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
