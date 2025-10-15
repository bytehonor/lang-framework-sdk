package com.bytehonor.sdk.framework.lang.cache;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.bytehonor.sdk.framework.lang.string.SpringString;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class SpringLocker {

    private final Cache<String, Boolean> cache;

    public SpringLocker(int size, long duration, TimeUnit unit) {
        cache = CacheBuilder.newBuilder().initialCapacity(size) // 设置初始容量为100
                .maximumSize(1024 * size) // 设置缓存的最大容量
                .expireAfterWrite(duration, unit) // 设置缓存在写入一分钟后失效
                .concurrencyLevel(10) // 设置并发级别为10
                .build(); // .recordStats() // 开启缓存统计
    }

    public boolean lock(String key) {
        Objects.requireNonNull(key, "key");

        if (SpringString.isEmpty(key)) {
            return false;
        }
        if (cache.getIfPresent(key) != null) {
            return false;
        }
        cache.put(key, true);
        return true;
    }

    public void invalidate(String key) {
        Objects.requireNonNull(key, "key");

        cache.invalidate(key);
    }
}
