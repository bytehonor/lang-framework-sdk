package com.bytehonor.sdk.lang.spring.thread;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DelayedSingleThread {

    private static final Logger LOG = LoggerFactory.getLogger(DelayedSingleThread.class);

    /**
     * 创建空的延迟队列
     */
    private final DelayQueue<DelayedTask<SafeTask>> queue = new DelayQueue<DelayedTask<SafeTask>>();

    /**
     * 线程
     */
    private final Thread thread;

    private DelayedSingleThread() {
        thread = new Thread(new WhileBlockTask() {

            @Override
            public void runThenBlock() throws InterruptedException {
                execute();
            }

        });
        thread.setName(getClass().getSimpleName());

        thread.start();
    }

    private static class LazyHolder {
        private static DelayedSingleThread SINGLE = new DelayedSingleThread();
    }

    private static DelayedSingleThread self() {
        return LazyHolder.SINGLE;
    }

    private void execute() throws InterruptedException {
        // 从延迟队列中取值,如果没有对象过期则队列一直等待，
        DelayedTask<SafeTask> t1 = queue.take();
        SafeTask runner = t1.getRunner();
        if (runner == null) {
            LOG.error("runner null");
            return;
        }
        runner.run();
    }

    /**
     * 往队列中添加任务
     * 
     * @param runner 任务
     * @param millis 延迟时间
     */
    public static void put(SafeTask runner, long millis) {
        // 转换成ns
        long nanoTime = TimeUnit.NANOSECONDS.convert(millis, TimeUnit.MILLISECONDS);
        DelayedTask<SafeTask> delayed = new DelayedTask<SafeTask>(nanoTime, runner);
        self().queue.put(delayed);
    }

    /**
     * 结束任务
     * 
     * @param task
     */
    public static boolean finish(DelayedTask<SafeTask> task) {
        return self().queue.remove(task);
    }
}
