package com.bytehonor.sdk.framework.lang.function.setter;

import com.bytehonor.sdk.framework.lang.function.ClassSetter;

/**
 * 模型 {@code T} 上 {@link String} 类型属性的 Setter（可序列化方法引用）。
 *
 * @author lijianqiang
 */
@FunctionalInterface
public interface SetString<T> extends ClassSetter<T, String> {

}