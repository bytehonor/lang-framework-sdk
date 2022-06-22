package com.bytehonor.sdk.beautify.lang.thread;

public interface QueueConsumer<T> {

    public void consume(T payload);
}
