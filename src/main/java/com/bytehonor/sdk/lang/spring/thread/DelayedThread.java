package com.bytehonor.sdk.lang.spring.thread;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DelayedThread {

    private static final Logger LOG = LoggerFactory.getLogger(DelayedThread.class);

    private DelayedThread() {
        init();
    }

    /**
     * 延迟加载(线程安全)
     *
     */
    private static class LazyHolder {
        private static DelayedThread itemQueueThread = new DelayedThread();
    }

    public static DelayedThread getInstance() {
        return LazyHolder.itemQueueThread;
    }

    /**
     * 缓存线程池
     */
    ExecutorService service = Executors.newSingleThreadExecutor();

    /**
     * 线程
     */
    private Thread daemonThread;

    /**
     * 初始化线程
     */
    private void init() {
        daemonThread = new Thread(() -> {
            try {
                execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        daemonThread.setName("SafeRunnerThread");
        LOG.info(">>>> start ...............");
        daemonThread.start();
    }

    private void execute() throws InterruptedException {
        while (true) {
            try {
                // 从延迟队列中取值,如果没有对象过期则队列一直等待，
                DelayedRunner<SafeRunner> t1 = item.take();
                if (t1 != null) {
                    final Runnable task = t1.getTask();
                    if (task == null) {
                        continue;
                    }
                    service.execute(task);
                }
            } catch (Exception e) {
                LOG.error("execute error", e);
            }
        }
    }

    /**
     * 创建空的延迟队列
     */
    private DelayQueue<DelayedRunner<SafeRunner>> item = new DelayQueue<DelayedRunner<SafeRunner>>();

    /**
     * 往队列中添加任务
     * 
     * @param time     延迟时间
     * @param task     任务
     * @param timeUnit 时间单位
     * 
     */
    public void put(SafeRunner task, long time, TimeUnit timeUnit) {
        // 转换成ns
        long nanoTime = TimeUnit.NANOSECONDS.convert(time, timeUnit);
        DelayedRunner<SafeRunner> delayed = new DelayedRunner<SafeRunner>(nanoTime, task);
        item.put(delayed);
    }

    /**
     * 结束任务
     * 
     * @param task
     */
    public boolean finish(DelayedRunner<SafeRunner> task) {
        return item.remove(task);
    }
}
