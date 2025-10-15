package com.bytehonor.sdk.framework.lang.thread;

/**
 * @author lijianqiang
 *
 */
public interface QueueBlockingProducer<T> {

    public T produce() throws InterruptedException;
}
