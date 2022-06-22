package com.bytehonor.sdk.lang.bytehonor.thread;

public interface QueueBlockingProducer<T> {

    public T produce() throws InterruptedException;
}
