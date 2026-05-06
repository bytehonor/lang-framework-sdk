package com.bytehonor.sdk.framework.lang.function;

import java.io.Serializable;
import java.util.function.Function;

/**
 * 可序列化的 {@link Function}，用于在 DSL 中以方法引用（如 {@code User::getName}）表达模型上的属性读取。
 *
 * @author lijianqiang
 */
@FunctionalInterface
public interface ClassGetter<T, R> extends Function<T, R>, Serializable {
}
