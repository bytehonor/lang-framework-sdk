package com.bytehonor.sdk.lang.spring.thread;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DelayedThread {

    private static final Logger LOG = LoggerFactory.getLogger(DelayedThread.class);

    /**
     * 创建空的延迟队列
     */
    private final DelayQueue<DelayedRunner<SafeRunner>> queue = new DelayQueue<DelayedRunner<SafeRunner>>();

    /**
     * 线程
     */
    private final Thread thread;

    private DelayedThread() {
        thread = new Thread(new WhileBlockRunner() {

            @Override
            public void runThenBlock() throws InterruptedException {
                execute();
            }

        });
        thread.setName(getClass().getSimpleName());

        thread.start();
    }

    /**
     * 延迟加载(线程安全)
     *
     */
    private static class LazyHolder {
        private static DelayedThread SINGLE = new DelayedThread();
    }

    public static DelayedThread getInstance() {
        return LazyHolder.SINGLE;
    }

    private void execute() throws InterruptedException {
        // 从延迟队列中取值,如果没有对象过期则队列一直等待，
        DelayedRunner<SafeRunner> t1 = queue.take();
        SafeRunner runner = t1.getRunner();
        if (runner == null) {
            LOG.error("runner null");
            return;
        }
        runner.run();
    }

    /**
     * 往队列中添加任务
     * 
     * @param time     延迟时间
     * @param runner   任务
     * @param timeUnit 时间单位
     * 
     */
    public void put(SafeRunner runner, long time, TimeUnit timeUnit) {
        // 转换成ns
        long nanoTime = TimeUnit.NANOSECONDS.convert(time, timeUnit);
        DelayedRunner<SafeRunner> delayed = new DelayedRunner<SafeRunner>(nanoTime, runner);
        queue.put(delayed);
    }

    /**
     * 结束任务
     * 
     * @param task
     */
    public boolean finish(DelayedRunner<SafeRunner> task) {
        return queue.remove(task);
    }
}
