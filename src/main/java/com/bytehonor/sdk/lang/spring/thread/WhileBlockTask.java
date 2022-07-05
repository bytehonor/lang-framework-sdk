package com.bytehonor.sdk.lang.spring.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class WhileBlockTask extends SafeTask {

    private static final Logger LOG = LoggerFactory.getLogger(WhileBlockTask.class);

    public abstract void runThenBlock() throws InterruptedException;

    @Override
    public final void runInSafe() {
        while (true) {
            try {
                runThenBlock();
            } catch (Exception e) {
                LOG.error("runThenBlock", e);
            }
        }
    }
}
