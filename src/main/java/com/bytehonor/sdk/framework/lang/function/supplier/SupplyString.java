package com.bytehonor.sdk.framework.lang.function.supplier;

import java.util.function.Supplier;

/**
 * 返回 {@link String} 的 {@link Supplier}（可序列化，便于与 Getters 等工具配合）。
 *
 * @author lijianqiang
 */
@FunctionalInterface
public interface SupplyString extends Supplier<String> {

}
