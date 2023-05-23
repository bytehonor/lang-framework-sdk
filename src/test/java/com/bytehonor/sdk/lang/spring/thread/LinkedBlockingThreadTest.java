package com.bytehonor.sdk.lang.spring.thread;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkedBlockingThreadTest {

    private static final Logger LOG = LoggerFactory.getLogger(LinkedBlockingThreadTest.class);

    @Test
    public void test() {
        LinkedBlockingThread<Integer> thread = LinkedBlockingThread.create(new QueueConsumer<Integer>() {

            @Override
            public void consume(Integer payload) {
                LOG.info("payload:{}", payload);
            }

        }).mount(getClass());

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
