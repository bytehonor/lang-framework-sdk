package com.bytehonor.sdk.framework.lang.thread;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkedThreadTest {

    private static final Logger LOG = LoggerFactory.getLogger(LinkedThreadTest.class);

    @Test
    public void test() {
        LinkedThread<Integer> thread = LinkedThread.create(new QueueConsumer<Integer>() {

            @Override
            public void consume(Integer payload) {
                LOG.info("payload:{}", payload);
            }
        }).mount(getClass());

        thread.start();

        for (int i = 0; i < 17; i++) {
            thread.add(i);
        }

        Sleep.millis(10000L);

        for (int i = 100; i < 137; i++) {
            thread.add(i);
        }

        Sleep.millis(30000L);

        LOG.info("main end");
    }

}
