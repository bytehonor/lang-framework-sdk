package com.bytehonor.sdk.framework.lang.string;

/**
 * 轻量链式 {@link StringBuilder} 封装，{@link #append(Object)} 会忽略 {@code null}。
 *
 * @author lijianqiang
 */
public final class StringCreator {

    private final StringBuilder sb;

    private StringCreator() {
        this.sb = new StringBuilder();
    }

    /**
     * @return 新的可追加实例
     */
    public static StringCreator create() {
        return new StringCreator();
    }

    /**
     * 追加 {@code val} 的 {@link String#valueOf(Object)}；{@code val} 为 {@code null} 时不追加。
     */
    public StringCreator append(Object val) {
        if (val != null) {
            sb.append(val);
        }
        return this;
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
