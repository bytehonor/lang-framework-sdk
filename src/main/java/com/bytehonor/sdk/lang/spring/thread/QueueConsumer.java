package com.bytehonor.sdk.lang.spring.thread;

/**
 * @author lijianqiang
 *
 */
public interface QueueConsumer<T> {

    public void consume(T payload);
}
