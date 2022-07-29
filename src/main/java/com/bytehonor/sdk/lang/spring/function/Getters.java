package com.bytehonor.sdk.lang.spring.function;

import com.bytehonor.sdk.lang.spring.function.getter.GetDouble;
import com.bytehonor.sdk.lang.spring.function.getter.GetInteger;
import com.bytehonor.sdk.lang.spring.function.getter.GetLong;
import com.bytehonor.sdk.lang.spring.function.getter.GetString;
import com.bytehonor.sdk.lang.spring.function.supplier.SupplyBoolean;
import com.bytehonor.sdk.lang.spring.function.supplier.SupplyDouble;
import com.bytehonor.sdk.lang.spring.function.supplier.SupplyInteger;
import com.bytehonor.sdk.lang.spring.function.supplier.SupplyLong;
import com.bytehonor.sdk.lang.spring.function.supplier.SupplyString;

public class Getters {

    public static <T> String fieldName(ClassGetter<T, ?> getter) {
        return SerializedLambdaUtils.getFieldName(getter);
    }

    public static <T> String get(GetString<T> getter, T t) {
        return getter.apply(t);
    }

    public static <T> Long get(GetLong<T> getter, T t) {
        return getter.apply(t);
    }

    public static <T> Integer get(GetInteger<T> getter, T t) {
        return getter.apply(t);
    }

    public static <T> Double get(GetDouble<T> getter, T t) {
        return getter.apply(t);
    }

    public static String get(SupplyString supplier) {
        return supplier.get();
    }

    public static Long get(SupplyLong supplier) {
        return supplier.get();
    }

    public static Integer get(SupplyInteger supplier) {
        return supplier.get();
    }

    public static Boolean get(SupplyBoolean supplier) {
        return supplier.get();
    }

    public static Double get(SupplyDouble supplier) {
        return supplier.get();
    }
}
