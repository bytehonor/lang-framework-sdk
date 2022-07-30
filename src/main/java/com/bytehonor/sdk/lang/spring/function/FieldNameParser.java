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

    private static final String IS = "is";
    private static final String GET = "get";
    private static final String SET = "set";

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

        if (methodName.startsWith(IS)) {
            fieldName = StringObject.uncapitalize(methodName.substring(IS.length()));
        } else if (methodName.startsWith(GET)) {
            fieldName = StringObject.uncapitalize(methodName.substring(GET.length()));
        } else if (methodName.startsWith(SET)) {
            fieldName = StringObject.uncapitalize(methodName.substring(SET.length()));
        } else {
            fieldName = methodName;
        }

        NAMES.put(methodName, fieldName);
        return fieldName;
    }

}
