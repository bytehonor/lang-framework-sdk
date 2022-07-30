package com.bytehonor.sdk.lang.spring.function;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.bytehonor.sdk.lang.spring.util.StringObject;

/**
 * 字段名解析器
 *
 */
public class FieldNameParser {

    private static final ConcurrentHashMap<String, String> NAMES = new ConcurrentHashMap<String, String>();

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
    public static String parseFieldName(String methodName) {
        Objects.requireNonNull(methodName, methodName);
        if (methodName.length() < 3) {
            return methodName;
        }
        String fieldName = NAMES.get(methodName);
        if (fieldName != null) {
            return fieldName;
        }
        if (methodName.startsWith("is")) {
            fieldName = StringObject.uncapitalize(methodName.substring("is".length()));
        } else {
            fieldName = StringObject.uncapitalize(methodName.substring("get".length()));
        }
        NAMES.put(methodName, fieldName);
        return fieldName;
    }
}
