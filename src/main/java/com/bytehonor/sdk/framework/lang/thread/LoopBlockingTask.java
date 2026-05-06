package com.bytehonor.sdk.framework.lang.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 无限循环执行 {@link #runThenBlock()}，单次异常被捕获后继续下一轮（不退出线程）。
 *
 * @author lijianqiang
 */
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
