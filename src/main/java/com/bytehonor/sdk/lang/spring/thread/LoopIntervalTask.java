package com.bytehonor.sdk.lang.spring.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LoopIntervalTask extends SafeTask {

    private static final Logger LOG = LoggerFactory.getLogger(LoopIntervalTask.class);

    public abstract long delays();

    public abstract long intervals();

    public abstract void runThenSleep();

    @Override
    public final void runInSafe() {
        long delays = delays();
        if (delays > 0) {
            LOG.info("delays:{}", delays);
            Sleep.millis(delays);
        }

        while (true) {
            try {
                runThenSleep();
            } catch (Exception e) {
                LOG.error("runThenSleep", e);
            }
            Sleep.millis(intervals());
        }
    }
}
