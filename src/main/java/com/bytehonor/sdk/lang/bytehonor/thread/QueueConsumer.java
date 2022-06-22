package com.bytehonor.sdk.lang.bytehonor.thread;

public interface QueueConsumer<T> {

    public void consume(T payload);
}
