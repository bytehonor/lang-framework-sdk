package com.bytehonor.sdk.framework.lang.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LoopBlockingTask extends SafeTask {

    private static final Logger LOG = LoggerFactory.getLogger(LoopBlockingTask.class);

    public abstract void runThenBlock() throws InterruptedException;

    @Override
    public final void handle() {
        while (true) {
            try {
                runThenBlock();
            } catch (Exception e) {
                LOG.error("runThenBlock", e);
            }
        }
    }
}
