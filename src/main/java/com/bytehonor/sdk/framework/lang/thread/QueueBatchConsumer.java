package com.bytehonor.sdk.framework.lang.thread;

import java.util.List;

/**
 * @author lijianqiang
 *
 */
public interface QueueBatchConsumer<T> {

    public int size();

    public void consume(List<T> payloads);
}
