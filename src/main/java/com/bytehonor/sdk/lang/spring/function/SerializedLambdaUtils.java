package com.bytehonor.sdk.lang.spring.function;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@link SerializedLambda}工具类
 *
 */
public class SerializedLambdaUtils {

    private static final ConcurrentHashMap<String, SerializedLambda> CACHE = new ConcurrentHashMap<String, SerializedLambda>();

    /**
     * 获取字段名称
     */
    public static <T> String getFieldName(ClassSetter<T, ?> classSetter) {
        return getFieldName(getSerializedLambda(classSetter));
    }

    /**
     * 获取字段名称
     */
    public static <T> String getFieldName(ClassGetter<T, ?> ClassGetter) {
        return getFieldName(getSerializedLambda(ClassGetter));
    }

    /**
     * 
     * <pre>
     * 获取lambda表达式字段名称
     * 假设你的lambda表达式部分是这样写的：Person::getFirstName
     * 那么，此方法的目的就是获取到getFirstName方法对应的（Person类中的对应字段的）字段名
     * </pre>
     */
    private static String getFieldName(SerializedLambda serializedLambda) {
        String implMethodName = getImplMethodName(serializedLambda);
        return FieldNameParser.parseFieldName(implMethodName);
    }

    /**
     * <pre>
     * 获取lambda表达式中，实现方法的方法名
     * 说明： 假设你的lambda表达式部分是这样写的：Person::getFirstName
     * 那么这里获取到的就是Person.getFirstName()的方法名getFirstName
     * </pre>
     *
     * @param serializedLambda serializedLambda对象
     * @return 实现方法的方法名 形如：getFirstName
     */
    private static String getImplMethodName(SerializedLambda serializedLambda) {
        return serializedLambda.getImplMethodName();
    }

    /**
     * <pre>
     * 获取lambda表达式中，实现方法的类的全类名 说明：
     * 假设你的lambda表达式部分是这样写的：Person::getFirstName，
     * 那么这里获取到的就是Person的全类名，形如：com.example.lambda.test.Person
     * </pre>
     *
     * @param serializedLambda serializedLambda对象
     * @return 实现方法的类的全类名 形如：com.example.lambda.test.Person
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
    private static <T extends Serializable> SerializedLambda getSerializedLambda(T potentialLambda) {
        Objects.requireNonNull(potentialLambda, "potentialLambda");
        Class<?> potentialLambdaClass = potentialLambda.getClass();
        String name = potentialLambdaClass.getName();
        SerializedLambda result = CACHE.get(name);
        if (result != null) {
            return result;
        }
        try {
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
            result = (SerializedLambda) writeReplaceObject;
            CACHE.put(name, result);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("potentialLambda must be lambda-class", e);
        }

        return result;
    }
}
