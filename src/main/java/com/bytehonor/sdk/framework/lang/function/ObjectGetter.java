package com.bytehonor.sdk.framework.lang.function;

/**
 * 无参供应型函数式接口，语义等价于 {@code () -> T}。
 *
 * @author lijianqiang
 */
@FunctionalInterface
public interface ObjectGetter<T> {
    T get();
}
