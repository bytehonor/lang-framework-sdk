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

    /**
     * Checks that the specified object reference is not {@code null} and throws a
     * customized {@link NullPointerException} if it is. This method is designed
     * primarily for doing parameter validation in methods and constructors with
     * multiple parameters, as demonstrated below: <blockquote>
     * 
     * <pre>
     * public Foo(Bar bar, Baz baz) {
     *     this.bar = Objects.requireNonNull(bar, "bar must not be null");
     *     this.baz = Objects.requireNonNull(baz, "baz must not be null");
     * }
     * </pre>
     * 
     * </blockquote>
     *
     * @param obj     the object reference to check for nullity
     * @param message detail message to be used in the event that a {@code
     *                NullPointerException} is thrown
     * @param <T>     the type of the reference
     * @return {@code obj} if not {@code null}
     * @throws NullPointerException if {@code obj} is {@code null}
     */
    public static <T> T requireNonNull(T obj, String message) {
        return Objects.requireNonNull(obj, message);
    }

    /**
     * Returns {@code true} if the provided reference is {@code null} otherwise
     * returns {@code false}.
     *
     * @apiNote This method exists to be used as a
     *          {@link java.util.function.Predicate},
     *          {@code filter(Objects::isNull)}
     *
     * @param obj a reference to be checked against {@code null}
     * @return {@code true} if the provided reference is {@code null} otherwise
     *         {@code false}
     *
     * @see java.util.function.Predicate
     * @since 1.8
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * Returns {@code true} if the provided reference is non-{@code null} otherwise
     * returns {@code false}.
     *
     * @apiNote This method exists to be used as a
     *          {@link java.util.function.Predicate},
     *          {@code filter(Objects::nonNull)}
     *
     * @param obj a reference to be checked against {@code null}
     * @return {@code true} if the provided reference is non-{@code null} otherwise
     *         {@code false}
     *
     * @see java.util.function.Predicate
     * @since 1.8
     */
    public static boolean nonNull(Object obj) {
        return obj != null;
    }

}
