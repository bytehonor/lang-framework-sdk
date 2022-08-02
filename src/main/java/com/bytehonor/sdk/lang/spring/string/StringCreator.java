package com.bytehonor.sdk.lang.spring.string;

public final class StringCreator {

    private final StringBuilder sb;

    private StringCreator() {
        this.sb = new StringBuilder();
    }

    public static StringCreator create() {
        return new StringCreator();
    }

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
