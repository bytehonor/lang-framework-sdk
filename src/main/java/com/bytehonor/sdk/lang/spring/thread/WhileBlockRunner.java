package com.bytehonor.sdk.lang.spring.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class WhileBlockRunner extends SafeRunner {

    private static final Logger LOG = LoggerFactory.getLogger(WhileBlockRunner.class);

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
