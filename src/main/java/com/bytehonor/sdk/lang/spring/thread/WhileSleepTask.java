package com.bytehonor.sdk.lang.spring.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class WhileSleepTask extends SafeTask {

    private static final Logger LOG = LoggerFactory.getLogger(WhileSleepTask.class);

    public abstract long millis();

    public abstract void runThenSleep();

    @Override
    public final void runInSafe() {
        while (true) {
            try {
                runThenSleep();
            } catch (Exception e) {
                LOG.error("runInWhile", e);
            }
            Sleeping.millis(millis());
        }
    }
}
