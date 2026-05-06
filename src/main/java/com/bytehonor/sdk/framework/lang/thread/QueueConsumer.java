package com.bytehonor.sdk.framework.lang.thread;

/**
 * 消费者：接收单个由生产者产出的元素并完成业务处理。
 *
 * @author lijianqiang
 *
 */
public interface QueueConsumer<T> {

    public void consume(T payload);
}
