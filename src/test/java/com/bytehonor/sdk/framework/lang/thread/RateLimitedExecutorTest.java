package com.bytehonor.sdk.framework.lang.thread;

import org.junit.Test;

import com.bytehonor.sdk.framework.lang.constant.TimeConstants;

public class RateLimitedExecutorTest {

    @Test
    public void test() {
        long intervals = 3000L;
        for (int i = 0; i < 100; i++) {
            RateLimitedExecutor.add(intervals, TestUser.of(i), TestUser::consume);
        }

        Sleep.millis(TimeConstants.HOUR);
    }

}
