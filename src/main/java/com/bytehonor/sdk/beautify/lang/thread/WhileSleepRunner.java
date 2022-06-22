package com.bytehonor.sdk.beautify.lang.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.define.bytehonor.task.SafeTask;

public abstract class WhileSleepRunner extends SafeTask {

    private static final Logger LOG = LoggerFactory.getLogger(WhileSleepRunner.class);

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
            sleep(millis());
        }
    }

    private void sleep(long millis) {
        if (millis < 1L) {
            return;
        }
        LOG.debug("sleep:{}", millis);
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            LOG.error("sleep", e);
        }
    }
}
