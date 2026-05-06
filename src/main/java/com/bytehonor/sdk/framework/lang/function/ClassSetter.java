package com.bytehonor.sdk.framework.lang.function;

import java.io.Serializable;
import java.util.function.BiConsumer;

/**
 * 可序列化的 {@link BiConsumer}，用于在 DSL 中以方法引用表达模型上的属性写入。
 *
 * @author lijianqiang
 */
@FunctionalInterface
public interface ClassSetter<T, U> extends BiConsumer<T, U>, Serializable {

}
