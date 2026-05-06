package com.bytehonor.sdk.framework.lang.thread;

/**
 * 阻塞型生产者：在阻塞任务循环中产出元素，允许在 {@link InterruptedException} 时中断等待。
 *
 * @author lijianqiang
 *
 */
public interface QueueBlockingProducer<T> {

    public T produce() throws InterruptedException;
}
