package com.bytehonor.sdk.lang.spring.validate;

import java.util.Objects;

import com.bytehonor.sdk.define.spring.exception.ParameterException;
import com.bytehonor.sdk.define.spring.lang.StringCreator;

public class StringValidate {

    public static void length(String src, int min, int max, String message) {
        Objects.requireNonNull(src, message);
        if (src.length() < min) {
            throw new ParameterException(
                    StringCreator.create().append(message).append(" length < ").append(min).toString());
        }
        if (src.length() > max) {
            throw new ParameterException(
                    StringCreator.create().append(message).append(" length > ").append(max).toString());
        }
    }
}
