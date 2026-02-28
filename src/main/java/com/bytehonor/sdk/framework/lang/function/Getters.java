package com.bytehonor.sdk.framework.lang.function;

import java.util.Objects;

import com.bytehonor.sdk.framework.lang.core.field.SerializedLambdaUtils;
import com.bytehonor.sdk.framework.lang.function.getter.GetBoolean;
import com.bytehonor.sdk.framework.lang.function.getter.GetDouble;
import com.bytehonor.sdk.framework.lang.function.getter.GetInteger;
import com.bytehonor.sdk.framework.lang.function.getter.GetLong;
import com.bytehonor.sdk.framework.lang.function.getter.GetString;
import com.bytehonor.sdk.framework.lang.function.supplier.SupplyBoolean;
import com.bytehonor.sdk.framework.lang.function.supplier.SupplyDouble;
import com.bytehonor.sdk.framework.lang.function.supplier.SupplyInteger;
import com.bytehonor.sdk.framework.lang.function.supplier.SupplyLong;
import com.bytehonor.sdk.framework.lang.function.supplier.SupplyString;

public class Getters {

    /**
     * 获取getter的属性名称，getUserName: userName
     * 
     * @param <T>
     * @param getter
     * @return
     */
    public static <T> String field(ClassGetter<T, ?> getter) {
        Objects.requireNonNull(getter, "getter");

        return SerializedLambdaUtils.getFieldName(getter);
    }

    public static <T> String get(GetString<T> getter, T model) {
        return getter.apply(model);
    }

    public static <T> Long get(GetLong<T> getter, T model) {
        return getter.apply(model);
    }

    public static <T> Integer get(GetInteger<T> getter, T model) {
        return getter.apply(model);
    }

    public static <T> Double get(GetDouble<T> getter, T model) {
        return getter.apply(model);
    }

    public static <T> Boolean get(GetBoolean<T> getter, T model) {
        return getter.apply(model);
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
