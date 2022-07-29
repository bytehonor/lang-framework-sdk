package com.bytehonor.sdk.lang.spring.function;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * {@link SerializedLambda}工具类
 *
 * https://blog.csdn.net/justry_deng/article/details/124059375
 */
public class SerializedLambdaUtils {

    public static FieldNameParser defaultFieldNameParser = new FieldNameParser() {
    };

    /**
     * @see SerializedLambdaUtils#getFieldName(ClassGetter)
     */
    public static <T> String getFieldName(ClassGetter<T, ?> classGetter) {
        return getFieldName(classGetter, defaultFieldNameParser);
    }

    /**
     * 获取字段名称
     */
    public static <T> String getFieldName(ClassGetter<T, ?> ClassGetter, FieldNameParser fieldNameParser) {
        return getFieldName(getSerializedLambda(ClassGetter), fieldNameParser);
    }

    /**
     * 
     * <pre>
     * 获取lambda表达式字段名称
     * 假设你的lambda表达式部分是这样写的：<code>Person::getFirstName</code>，
     * 那么，此方法的目的就是获取到getFirstName方法对应的（Person类中的对应字段的）字段名
     * </pre>
     */
    public static String getFieldName(SerializedLambda serializedLambda, FieldNameParser fieldNameParser) {
        String implClassLongName = getImplClassLongName(serializedLambda);
        String implMethodName = getImplMethodName(serializedLambda);
        try {
            return fieldNameParser.parseFieldName(Class.forName(implClassLongName), implMethodName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <pre>
     * 获取lambda表达式中，实现方法的方法名
     * 说明： 假设你的lambda表达式部分是这样写的：<code>Person::getFirstName</code>，
     * 那么这里获取到的就是Person.getFirstName()的方法名getFirstName
     * </pre>
     *
     * @param serializedLambda serializedLambda对象
     * @return 实现方法的方法名 <br />
     *         形如：getFirstName
     */
    private static String getImplMethodName(SerializedLambda serializedLambda) {
        return serializedLambda.getImplMethodName();
    }

    /**
     * <pre>
     * 获取lambda表达式中，实现方法的类的全类名 说明：
     * 假设你的lambda表达式部分是这样写的：<code>Person::getFirstName</code>，
     * 那么这里获取到的就是Person的全类名，形如：<code>com.example.lambda.test.Person</code>
     * </pre>
     *
     * @param serializedLambda serializedLambda对象
     * @return 实现方法的类的全类名 <br />
     *         形如：com.example.lambda.test.Person
     */
    private static String getImplClassLongName(SerializedLambda serializedLambda) {
        return serializedLambda.getImplClass().replace("/", ".");
    }

    /**
     * 获取SerializedLambda实例
     *
     * @param potentialLambda lambda实例
     * @return SerializedLambda实例
     */
    private static <T extends Serializable> SerializedLambda getSerializedLambda(T potentialLambda) {
        try {
            Class<?> potentialLambdaClass = potentialLambda.getClass();
            // lambda类属于合成类
            if (!potentialLambdaClass.isSynthetic()) {
                throw new IllegalArgumentException("potentialLambda must be lambda-class");
            }
            Method writeReplaceMethod = potentialLambdaClass.getDeclaredMethod("writeReplace");
            // boolean isAccessible = writeReplaceMethod.canAccess(potentialLambda);
            // writeReplaceMethod.setAccessible(isAccessible);
            writeReplaceMethod.setAccessible(true);
            Object writeReplaceObject = writeReplaceMethod.invoke(potentialLambda);
            if (writeReplaceObject == null || !SerializedLambda.class.isAssignableFrom(writeReplaceObject.getClass())) {
                throw new IllegalArgumentException("writeReplaceObject should not be " + writeReplaceObject);
            }
            return (SerializedLambda) writeReplaceObject;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("potentialLambda must be lambda-class", e);
        }
    }

    /**
     * 字段名解析器
     *
     * @author JustryDeng
     * @since 2022/4/9 11:31
     */
    public interface FieldNameParser {

        /**
         * <pre>
         * 解析字段名
         * 
         * 假设你的lambda表达式部分是这样写的：<code>Person::getFirstName</code>，
         * 那么，
         * clazz就对应Person类
         * methodName就对应getFirstName
         * </pre>
         *
         * @param clazz      字段所在的类
         * @param methodName 与字段相关的方法（如：该字段的getter方法）
         * @return 解析字段名
         */
        default String parseFieldName(Class<?> clazz, String methodName) {
            return uncapitalize(methodName.substring("get".length()));
        }
    }

    public static String uncapitalize(final String str) {
        final int strLen = length(str);
        if (strLen == 0) {
            return str;
        }

        final int firstCodepoint = str.codePointAt(0);
        final int newCodePoint = Character.toLowerCase(firstCodepoint);
        if (firstCodepoint == newCodePoint) {
            // already capitalized
            return str;
        }

        final int newCodePoints[] = new int[strLen]; // cannot be longer than the char array
        int outOffset = 0;
        newCodePoints[outOffset++] = newCodePoint; // copy the first codepoint
        for (int inOffset = Character.charCount(firstCodepoint); inOffset < strLen;) {
            final int codepoint = str.codePointAt(inOffset);
            newCodePoints[outOffset++] = codepoint; // copy the remaining ones
            inOffset += Character.charCount(codepoint);
        }
        return new String(newCodePoints, 0, outOffset);
    }

    public static int length(final CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }

}
