package com.bytehonor.sdk.lang.spring.thread;

import org.junit.Test;

import com.bytehonor.sdk.lang.spring.constant.TimeConstants;

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
