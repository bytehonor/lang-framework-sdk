package com.bytehonor.sdk.framework.lang.cache;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.bytehonor.sdk.framework.lang.string.StringKit;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * 基于 Guava {@link Cache} 的进程内互斥标记：同一 key 在过期前仅允许首次 {@link #lock(String)} 成功。
 *
 * @author lijianqiang
 */
public class SpringLocker {

    private final Cache<String, Boolean> cache;

    /**
     * @param size     初始容量（同时影响最大条目数上限为 1024 * size）
     * @param duration 写入后经过多久条目过期
     * @param unit     时间单位
     */
    public SpringLocker(int size, long duration, TimeUnit unit) {
        cache = CacheBuilder.newBuilder().initialCapacity(size) // 设置初始容量为100
                .maximumSize(1024 * size) // 设置缓存的最大容量
                .expireAfterWrite(duration, unit) // 设置缓存在写入一分钟后失效
                .concurrencyLevel(10) // 设置并发级别为10
                .build(); // .recordStats() // 开启缓存统计
    }

    /**
     * 尝试占用 key：若 key 为空、已存在或刚被占用则返回 false，否则写入 true 并返回 true。
     *
     * @param key 非空非空白字符串
     * @return 是否成功获得“锁”标记
     */
    public boolean lock(String key) {
        Objects.requireNonNull(key, "key");

        if (StringKit.isEmpty(key)) {
            return false;
        }
        if (cache.getIfPresent(key) != null) {
            return false;
        }
        cache.put(key, true);
        return true;
    }

    /**
     * 立即移除 key 对应的缓存条目，便于提前释放“锁”。
     *
     * @param key 非空 key
     */
    public void invalidate(String key) {
        Objects.requireNonNull(key, "key");

        cache.invalidate(key);
    }
}
