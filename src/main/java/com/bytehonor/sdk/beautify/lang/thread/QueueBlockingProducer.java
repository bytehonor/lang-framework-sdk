package com.bytehonor.sdk.beautify.lang.thread;

/**
 * @author lijianqiang
 *
 */
public interface QueueBlockingProducer<T> {

    public T produce() throws InterruptedException;
}
