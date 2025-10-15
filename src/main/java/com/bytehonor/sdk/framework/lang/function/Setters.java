package com.bytehonor.sdk.framework.lang.function;

import com.bytehonor.sdk.framework.lang.function.consumer.ConsumeBoolean;
import com.bytehonor.sdk.framework.lang.function.consumer.ConsumeDouble;
import com.bytehonor.sdk.framework.lang.function.consumer.ConsumeInteger;
import com.bytehonor.sdk.framework.lang.function.consumer.ConsumeLong;
import com.bytehonor.sdk.framework.lang.function.consumer.ConsumeString;
import com.bytehonor.sdk.framework.lang.function.setter.SetBoolean;
import com.bytehonor.sdk.framework.lang.function.setter.SetDouble;
import com.bytehonor.sdk.framework.lang.function.setter.SetInteger;
import com.bytehonor.sdk.framework.lang.function.setter.SetLong;
import com.bytehonor.sdk.framework.lang.function.setter.SetString;

public class Setters {

    public static <T> String field(ClassSetter<T, ?> setter) {
        return SerializedLambdaUtils.getFieldName(setter);
    }

    public static <T> void set(SetString<T> setter, T model, String val) {
        setter.accept(model, val);
    }

    public static <T> void set(SetLong<T> setter, T model, Long val) {
        setter.accept(model, val);
    }

    public static <T> void set(SetInteger<T> setter, T model, Integer val) {
        setter.accept(model, val);
    }

    public static <T> void set(SetBoolean<T> setter, T model, Boolean val) {
        setter.accept(model, val);
    }

    public static <T> void set(SetDouble<T> setter, T model, Double val) {
        setter.accept(model, val);
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
