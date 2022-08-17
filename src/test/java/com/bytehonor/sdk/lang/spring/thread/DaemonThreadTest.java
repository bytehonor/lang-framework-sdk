package com.bytehonor.sdk.lang.spring.thread;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaemonThreadTest {

    private static final Logger LOG = LoggerFactory.getLogger(DaemonThreadTest.class);

    @Test
    public void test() {
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    LOG.info("我是子线程(用户线程.I am running");
                } catch (Exception e) {
                    LOG.error("error", e);
                }
            }
        });
        // 标记为守护线程
        // t1.setDaemon(true);
        // 启动线程
        t1.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            LOG.error("sleep", e);
        }
        LOG.info("主线程执行完毕...");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    LOG.info("我是子线程(用户线程.I am running");
                } catch (Exception e) {
                    LOG.error("error", e);
                }
            }
        });
        // 标记为守护线程
        // t1.setDaemon(true);
        // 启动线程
        t1.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            LOG.error("sleep", e);
        }
        LOG.info("主线程执行完毕...");
    }

}
