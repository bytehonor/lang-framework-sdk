package com.bytehonor.sdk.framework.lang.function.consumer;

import java.util.function.Consumer;

/**
 * 接受单个 {@link String} 参数的 {@link Consumer}（可序列化，便于与 Setters 等工具配合）。
 *
 * @author lijianqiang
 */
@FunctionalInterface
public interface ConsumeString extends Consumer<String> {

}
