package com.bytehonor.sdk.framework.lang.thread;

/**
 * @author lijianqiang
 *
 */
public interface QueueConsumer<T> {

    public void consume(T payload);
}
