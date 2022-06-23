package com.bytehonor.sdk.lang.spring.thread;

import java.util.List;

/**
 * @author lijianqiang
 *
 */
public interface QueueBatchConsumer<T> {

    public int size();

    public void consume(List<T> payloads);
}
