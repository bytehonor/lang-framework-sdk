package com.bytehonor.sdk.beautify.lang.thread;

public interface QueueBlockingProducer<T> {

    public T produce() throws InterruptedException;
}
