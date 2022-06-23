package com.bytehonor.sdk.lang.spring.function;

@FunctionalInterface
public interface ObjectGetter<T> {
    T get();
}
