package com.bytehonor.sdk.lang.spring.function;

import com.bytehonor.sdk.lang.spring.function.consumer.ConsumeBoolean;
import com.bytehonor.sdk.lang.spring.function.consumer.ConsumeDouble;
import com.bytehonor.sdk.lang.spring.function.consumer.ConsumeInteger;
import com.bytehonor.sdk.lang.spring.function.consumer.ConsumeLong;
import com.bytehonor.sdk.lang.spring.function.consumer.ConsumeString;
import com.bytehonor.sdk.lang.spring.function.setter.SetBoolean;
import com.bytehonor.sdk.lang.spring.function.setter.SetDouble;
import com.bytehonor.sdk.lang.spring.function.setter.SetInteger;
import com.bytehonor.sdk.lang.spring.function.setter.SetLong;
import com.bytehonor.sdk.lang.spring.function.setter.SetString;

public class Setters {

    public static <T> String fieldName(ClassSetter<T, ?> setter) {
        return SerializedLambdaUtils.getFieldName(setter);
    }

    public static <T> void set(SetString<T> setter, T t, String val) {
        setter.accept(t, val);
    }

    public static <T> void set(SetLong<T> setter, T t, Long val) {
        setter.accept(t, val);
    }

    public static <T> void set(SetInteger<T> setter, T t, Integer val) {
        setter.accept(t, val);
    }

    public static <T> void set(SetBoolean<T> setter, T t, Boolean val) {
        setter.accept(t, val);
    }

    public static <T> void set(SetDouble<T> setter, T t, Double val) {
        setter.accept(t, val);
    }

    public static void set(ConsumeString consumer, String val) {
        consumer.accept(val);
    }

    public static void set(ConsumeLong consumer, Long val) {
        consumer.accept(val);
    }

    public static void set(ConsumeInteger consumer, Integer val) {
        consumer.accept(val);
    }

    public static void set(ConsumeBoolean consumer, Boolean val) {
        consumer.accept(val);
    }

    public static void set(ConsumeDouble consumer, Double val) {
        consumer.accept(val);
    }
}
