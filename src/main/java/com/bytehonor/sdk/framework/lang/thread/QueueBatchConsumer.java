package com.bytehonor.sdk.framework.lang.thread;

import java.util.List;

/**
 * 批量消费者：声明批次大小 {@link #size()} 并一次性处理列表中的多个元素。
 *
 * @author lijianqiang
 *
 */
public interface QueueBatchConsumer<T> {

    public int size();

    public void consume(List<T> payloads);
}
