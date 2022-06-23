package com.bytehonor.sdk.lang.spring.thread;

/**
 * @author lijianqiang
 *
 */
public interface QueueProducer<T> {

    public T produce();
}
