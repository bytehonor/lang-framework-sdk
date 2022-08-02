package com.bytehonor.sdk.lang.spring.thread;

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
        }, "test");

        thread.start();

        for (int i = 0; i < 17; i++) {
            thread.add(i);
        }

        Sleeping.sleep(10000L);

        for (int i = 100; i < 137; i++) {
            thread.add(i);
        }

        Sleeping.sleep(30000L);

        LOG.info("main end");
    }

}
