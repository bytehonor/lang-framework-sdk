package com.bytehonor.sdk.lang.spring.match;

import java.util.Collection;

import com.bytehonor.sdk.lang.spring.constant.JavaValueTypes;
import com.bytehonor.sdk.lang.spring.constant.SqlOperator;
import com.bytehonor.sdk.lang.spring.exception.SpringLangException;

/**
 * @author lijianqiang
 *
 */
public class KeyMatcher {

    /**
     * 忽略驼峰及下划线风格
     */
    private final String key;

    /**
     * String Long Integer Boolean Collection
     */
    private final Object value;

    /**
     * java type
     */
    private final String type;

    private final SqlOperator operator;

    private KeyMatcher(String key, Object value, String type, SqlOperator operator) {
        this.key = key;
        this.value = value;
        this.type = type;
        this.operator = operator;
    }

    public static KeyMatcher non() {
        return new KeyMatcher("", "", JavaValueTypes.STRING, SqlOperator.EQ);
    }

    public static KeyMatcher of(String key, Object value, String type, SqlOperator operator) {
        if (SqlOperator.LIKE.equals(operator) && JavaValueTypes.STRING.equals(type) == false) {
            throw new SpringLangException(key + " cannt be like");
        }
        if (SqlOperator.LIKE_LEFT.equals(operator) && JavaValueTypes.STRING.equals(type) == false) {
            throw new SpringLangException(key + " cannt be like");
        }
        if (SqlOperator.LIKE_RIGHT.equals(operator) && JavaValueTypes.STRING.equals(type) == false) {
            throw new SpringLangException(key + " cannt be like");
        }
        return new KeyMatcher(key, value, type, operator);
    }

    public static boolean accept(KeyMatcher matcher) {
        if (matcher == null) {
            return false;
        }
        return matcher.getOperator() != null && matcher.getKey() != null && matcher.getValue() != null;
    }

    /**
     * 
     * <pre>
     * 等于
     * </pre>
     * 
     * @param key
     * @param value
     * @return
     */
    public static KeyMatcher eq(String key, String value) {
        return of(key, value, JavaValueTypes.STRING, SqlOperator.EQ);
    }

    /**
     * 
     * <pre>
     * 等于
     * </pre>
     * 
     * @param key
     * @param value
     * @return
     */
    public static KeyMatcher eq(String key, Long value) {
        return of(key, value, JavaValueTypes.LONG, SqlOperator.EQ);
    }

    /**
     * 
     * <pre>
     * 等于
     * </pre>
     * 
     * @param key
     * @param value
     * @return
     */
    public static KeyMatcher eq(String key, Integer value) {
        return of(key, value, JavaValueTypes.INTEGER, SqlOperator.EQ);
    }

    /**
     * 
     * <pre>
     * 等于
     * </pre>
     * 
     * @param key
     * @param value
     * @return
     */
    public static KeyMatcher eq(String key, Boolean value) {
        return of(key, value, JavaValueTypes.BOOLEAN, SqlOperator.EQ);
    }

    /**
     * 
     * <pre>
     * 不等于
     * </pre>
     * 
     * @param key
     * @param value
     * @return
     */
    public static KeyMatcher neq(String key, String value) {
        return of(key, value, JavaValueTypes.STRING, SqlOperator.NEQ);
    }

    /**
     * 
     * <pre>
     * 不等于
     * </pre>
     * 
     * @param key
     * @param value
     * @return
     */
    public static KeyMatcher neq(String key, Long value) {
        return of(key, value, JavaValueTypes.LONG, SqlOperator.NEQ);
    }

    /**
     * 
     * <pre>
     * 不等于
     * </pre>
     * 
     * @param key
     * @param value
     * @return
     */
    public static KeyMatcher neq(String key, Integer value) {
        return of(key, value, JavaValueTypes.INTEGER, SqlOperator.NEQ);
    }

    /**
     * 
     * <pre>
     * 不等于
     * </pre>
     * 
     * @param key
     * @param value
     * @return
     */
    public static KeyMatcher neq(String key, Boolean value) {
        return of(key, value, JavaValueTypes.BOOLEAN, SqlOperator.NEQ);
    }

    /**
     * <pre>
     * 大于
     * </pre>
     * 
     * @param key
     * @param value
     * @return
     */
    public static KeyMatcher gt(String key, Long value) {
        return of(key, value, JavaValueTypes.LONG, SqlOperator.GT);
    }

    /**
     * <pre>
     * 大于
     * </pre>
     * 
     * @param key
     * @param value
     * @return
     */
    public static KeyMatcher gt(String key, Integer value) {
        return of(key, value, JavaValueTypes.INTEGER, SqlOperator.GT);
    }

    /**
     * <pre>
     * 大于等于
     * </pre>
     * 
     * @param key
     * @param value
     * @return
     */
    public static KeyMatcher egt(String key, Long value) {
        return of(key, value, JavaValueTypes.LONG, SqlOperator.EGT);
    }

    /**
     * <pre>
     * 大于等于
     * </pre>
     * 
     * @param key
     * @param value
     * @return
     */
    public static KeyMatcher egt(String key, Integer value) {
        return of(key, value, JavaValueTypes.INTEGER, SqlOperator.EGT);
    }

    /**
     * <pre>
     * 小于
     * </pre>
     * 
     * @param key
     * @param value
     * @return
     */
    public static KeyMatcher lt(String key, Long value) {
        return of(key, value, JavaValueTypes.LONG, SqlOperator.LT);
    }

    /**
     * <pre>
     * 小于
     * </pre>
     * 
     * @param key
     * @param value
     * @return
     */
    public static KeyMatcher lt(String key, Integer value) {
        return of(key, value, JavaValueTypes.INTEGER, SqlOperator.LT);
    }

    /**
     * <pre>
     * 小于等于
     * </pre>
     * 
     * @param key
     * @param value
     * @return
     */
    public static KeyMatcher elt(String key, Long value) {
        return of(key, value, JavaValueTypes.LONG, SqlOperator.ELT);
    }

    /**
     * <pre>
     * 小于等于
     * </pre>
     * 
     * @param key
     * @param value
     * @return
     */
    public static KeyMatcher elt(String key, Integer value) {
        return of(key, value, JavaValueTypes.INTEGER, SqlOperator.ELT);
    }

    public static KeyMatcher like(String key, String value) {
        return of(key, value, JavaValueTypes.STRING, SqlOperator.LIKE);
    }

    public static KeyMatcher likeLeft(String key, String value) {
        return of(key, value, JavaValueTypes.STRING, SqlOperator.LIKE_LEFT);
    }

    public static KeyMatcher likeRight(String key, String value) {
        return of(key, value, JavaValueTypes.STRING, SqlOperator.LIKE_RIGHT);
    }

    public static <T> KeyMatcher in(String key, Collection<T> values, Class<T> type) {
        return of(key, values, type.getName(), SqlOperator.IN);
    }

    public static <T> KeyMatcher in(String key, Collection<T> values, String type) {
        return of(key, values, type, SqlOperator.IN);
    }

//    public static KeyMatcher ins(String key, Collection<String> value) {
//        return of(key, value, JavaValueTypes.STRING, SqlOperator.IN);
//    }
//
//    public static KeyMatcher inl(String key, Collection<Long> value) {
//        return of(key, value, JavaValueTypes.LONG, SqlOperator.IN);
//    }
//
//    public static KeyMatcher ini(String key, Collection<Integer> value) {
//        return of(key, value, JavaValueTypes.INTEGER, SqlOperator.IN);
//    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public SqlOperator getOperator() {
        return operator;
    }

    public String uuid() {
        return new StringBuilder().append(key).append("-").append(operator.getKey()).toString();
    }

}
