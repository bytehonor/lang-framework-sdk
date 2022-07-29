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
public class SerializedLambdaUtil {

    public static FieldNameParser defaultFieldNameParser = new FieldNameParser() {
    };

    public static <T> String getFieldName(ClassSetter<T, ?> classGetter) {
        return getFieldName(classGetter, defaultFieldNameParser);
    }

    /**
     * 获取字段名称
     */
    public static <T> String getFieldName(ClassSetter<T, ?> ClassGetter, FieldNameParser fieldNameParser) {
        return getFieldName(getSerializedLambda(ClassGetter), fieldNameParser);
    }

    /**
     * @see SerializedLambdaUtil#getFieldName(ClassGetter)
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
        // String implClassLongName = getImplClassLongName(serializedLambda);
        String implMethodName = getImplMethodName(serializedLambda);
        return fieldNameParser.parseFieldName(implMethodName);
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
    public static String getImplClassLongName(SerializedLambda serializedLambda) {
        return serializedLambda.getImplClass().replace("/", ".");
    }

    /**
     * 获取SerializedLambda实例
     *
     * @param potentialLambda lambda实例
     * @return SerializedLambda实例
     */
    public static <T extends Serializable> SerializedLambda getSerializedLambda(T potentialLambda) {
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
}
