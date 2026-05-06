package com.bytehonor.sdk.framework.lang.cache;

import java.util.concurrent.TimeUnit;

/**
 * {@link SpringLocker} 的全局单例封装（默认初始容量 4096、写入后 4 小时过期），供静态方法快速使用。
 *
 * @author lijianqiang
 */
public class CacheLocker {

    private final SpringLocker locker;

    private CacheLocker() {
        locker = new SpringLocker(4096, 4, TimeUnit.HOURS);
    }

    private static class LazyHolder {
        private static CacheLocker SINGLE = new CacheLocker();
    }

    /**
     * @return 全局单例
     */
    public static CacheLocker self() {
        return LazyHolder.SINGLE;
    }

    /**
     * 委托给全局 {@link SpringLocker} 尝试占用 key。
     *
     * @param key 业务键
     * @return 是否成功获得标记
     */
    public static boolean lock(String key) {
        return self().locker.lock(key);
    }

    /**
     * 委托给全局 {@link SpringLocker} 移除 key 对应条目。
     *
     * @param key 业务键
     */
    public static void invalidate(String key) {
        self().locker.invalidate(key);
    }
}
