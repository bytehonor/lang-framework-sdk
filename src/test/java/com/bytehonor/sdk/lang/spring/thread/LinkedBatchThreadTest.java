package com.bytehonor.sdk.lang.spring.thread;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

public class LinkedBatchThreadTest {

    private static final Logger LOG = LoggerFactory.getLogger(LinkedBatchThreadTest.class);

    @Test
    public void test() {
        LinkedBatchThread<Integer> thread = LinkedBatchThread.create(new QueueBatchConsumer<Integer>() {

            @Override
            public int size() {
                return 10;
            }

            @Override
            public void consume(List<Integer> payloads) {
                if (CollectionUtils.isEmpty(payloads)) {
                    LOG.info("payloads empty");
                    return;
                }
                LOG.info("payloads:{}", payloads);

            }
        }, "test");

        thread.start();

        for (int i = 0; i < 17; i++) {
            thread.add(i);
        }

        ThreadSleep.sleep(10000L);

        for (int i = 100; i < 137; i++) {
            thread.add(i);
        }

        ThreadSleep.sleep(30000L);

        LOG.info("main end");
    }

}
