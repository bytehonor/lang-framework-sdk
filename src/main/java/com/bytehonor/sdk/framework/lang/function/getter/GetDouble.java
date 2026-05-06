package com.bytehonor.sdk.framework.lang.function.getter;

import com.bytehonor.sdk.framework.lang.function.ClassGetter;

/**
 * 模型 {@code T} 上 {@link Double} 类型属性的 Getter，可与查询 DSL 等配合解析字段名。
 *
 * @author lijianqiang
 */
@FunctionalInterface
public interface GetDouble<T> extends ClassGetter<T, Double> {
}
