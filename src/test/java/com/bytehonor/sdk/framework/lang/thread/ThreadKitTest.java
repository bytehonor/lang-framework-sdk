package com.bytehonor.sdk.framework.lang.thread;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadKitTest {

    private static final Logger LOG = LoggerFactory.getLogger(ThreadKitTest.class);

    @Test
    public void test() {
        String name = "ThreadIdGeneratorTest";
        for (int i = 0; i < 20; i++) {
            LOG.info("i:{}, id:{}", i, ThreadKit.id(name));
        }
    }

}
