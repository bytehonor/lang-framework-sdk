package com.bytehonor.sdk.lang.spring;

import java.util.Comparator;
import java.util.Objects;

/**
 * @author lijianqiang
 */
public final class Java {

    private Java() {
        throw new AssertionError("No java.util.Objects instances for you!");
    }

    public static boolean isEmpty(String str) {
        return (str == null || str.isEmpty());
    }

    public static boolean equals(Object a, Object b) {
        return Objects.equals(a, b);
    }

    public static boolean deepEquals(Object a, Object b) {
        return Objects.deepEquals(a, b);
    }

    public static int hashCode(Object o) {
        return Objects.hashCode(o);
    }

    public static int hash(Object... values) {
        return Objects.hash(values);
    }

    public static String toString(Object o) {
        return String.valueOf(o);
    }

    public static String toString(Object o, String nullDefault) {
        return Objects.toString(o, nullDefault);
    }

    public static <T> int compare(T a, T b, Comparator<? super T> c) {
        return Objects.compare(a, b, c);
    }

    public static <T> T requireNonNull(T obj) {
        return Objects.requireNonNull(obj);
    }

    public static <T> T requireNonNull(T obj, String message) {
        return Objects.requireNonNull(obj, message);
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean nonNull(Object obj) {
        return obj != null;
    }

}
