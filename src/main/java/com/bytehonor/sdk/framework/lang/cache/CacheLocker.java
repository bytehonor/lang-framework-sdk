package com.bytehonor.sdk.framework.lang.cache;

import java.util.concurrent.TimeUnit;

public class CacheLocker {

    private final SpringLocker locker;

    private CacheLocker() {
        locker = new SpringLocker(4096, 4, TimeUnit.HOURS);
    }

    private static class LazyHolder {
        private static CacheLocker SINGLE = new CacheLocker();
    }

    public static CacheLocker self() {
        return LazyHolder.SINGLE;
    }

    public static boolean lock(String key) {
        return self().locker.lock(key);
    }

    public static void invalidate(String key) {
        self().locker.invalidate(key);
    }
}
