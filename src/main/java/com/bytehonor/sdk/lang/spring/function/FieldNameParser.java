package com.bytehonor.sdk.lang.spring.function;

import java.util.Objects;

import com.bytehonor.sdk.lang.spring.util.StringObject;

/**
 * 字段名解析器
 *
 */
public interface FieldNameParser {

    /**
     * <pre>
     * 解析字段名
     * 
     * 假设你的lambda表达式部分是这样写的：Person::getFirstName，
     * 那么，
     * clazz就对应Person类
     * methodName就对应getFirstName
     * </pre>
     *
     * @param methodName 与字段相关的方法（如：该字段的getter方法）
     * @return 解析字段名
     */
    default String parseFieldName(String methodName) {
        Objects.requireNonNull(methodName, methodName);

        if (methodName.startsWith("is")) {
            return StringObject.uncapitalize(methodName.substring("is".length()));
        }
        return StringObject.uncapitalize(methodName.substring("get".length()));
    }
}
