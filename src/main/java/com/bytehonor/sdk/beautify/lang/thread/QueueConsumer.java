package com.bytehonor.sdk.beautify.lang.thread;

/**
 * @author lijianqiang
 *
 */
public interface QueueConsumer<T> {

    public void consume(T payload);
}
