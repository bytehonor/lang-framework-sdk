package com.bytehonor.sdk.lang.spring;

import java.util.Objects;

public class Sdk {

    public static void require(Object model, Long id) {
        if (model == null) {
            throw new NullPointerException("id:" + id);
        }
    }

    public static void require(Object model, String msg) {
        Objects.requireNonNull(model, msg);
    }
}
