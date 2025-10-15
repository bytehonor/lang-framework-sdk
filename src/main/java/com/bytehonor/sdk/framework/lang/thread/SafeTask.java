package com.bytehonor.sdk.framework.lang.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lijianqiang
 *
 */
public abstract class SafeTask implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(SafeTask.class);

    @Override
    public final void run() {
        try {
            handle();
        } catch (Exception e) {
            LOG.error("SafeTask error", e);
        }
    }

    public abstract void handle();
}
