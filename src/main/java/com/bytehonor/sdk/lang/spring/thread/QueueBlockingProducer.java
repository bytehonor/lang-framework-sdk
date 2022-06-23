package com.bytehonor.sdk.lang.spring.thread;

/**
 * @author lijianqiang
 *
 */
public interface QueueBlockingProducer<T> {

    public T produce() throws InterruptedException;
}
