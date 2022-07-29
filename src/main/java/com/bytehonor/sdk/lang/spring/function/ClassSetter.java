package com.bytehonor.sdk.lang.spring.function;

import java.io.Serializable;
import java.util.function.BiConsumer;

@FunctionalInterface
public interface ClassSetter<T, U> extends BiConsumer<T, U>, Serializable {

}
