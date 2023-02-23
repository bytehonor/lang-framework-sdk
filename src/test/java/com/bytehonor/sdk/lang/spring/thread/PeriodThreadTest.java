package com.bytehonor.sdk.lang.spring.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PeriodThreadTest {

    private static final Logger LOG = LoggerFactory.getLogger(PeriodThreadTest.class);

    public static void main(String[] args) {
        PeriodThread pt = new PeriodThread("test", new WhileSleepTask() {

            private int count = 0;

            @Override
            public long delays() {
                return 2000L;
            }

            @Override
            public long millis() {
                return 500L;
            }

            @Override
            public void runThenSleep() {
                LOG.info("count:{}", count);
                count++;
            }

        });
        pt.start();

        for (int i = 0; i <= 100; i++) {
            LOG.info("{}, i:{}", Thread.currentThread().getName(), i);
        }
        // 100打印完才能cout0
    }

}
