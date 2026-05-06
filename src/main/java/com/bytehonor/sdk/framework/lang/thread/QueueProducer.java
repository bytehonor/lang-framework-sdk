package com.bytehonor.sdk.framework.lang.thread;

/**
 * 生产者：在链式任务模板中每次产出一个待消费元素（可为 null 表示暂无数据）。
 *
 * @author lijianqiang
 *
 */
public interface QueueProducer<T> {

    public T produce();
}
