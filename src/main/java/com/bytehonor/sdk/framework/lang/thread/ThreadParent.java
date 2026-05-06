package com.bytehonor.sdk.framework.lang.thread;

/**
 * 可启动的后台线程抽象（由各类 {@code *Thread} 实现包装 {@link Thread#start()}）。
 *
 * @author lijianqiang
 */
public interface ThreadParent {

    public void start();
}
