package com.bytehonor.sdk.lang.spring.match;

import java.util.Collection;

import com.bytehonor.sdk.lang.spring.constant.JavaValueTypes;
import com.bytehonor.sdk.lang.spring.constant.SqlOperator;

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

    public static boolean accept(KeyMatcher matcher) {
        if (matcher == null) {
            return false;
        }
        return matcher.getOperator() != null && matcher.getKey() != null && matcher.getValue() != null;
    }

    public static String type(Object value) {
        return value != null ? value.getClass().getName() : "";
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
    public static KeyMatcher eq(String key, Object value) {
        return new KeyMatcher(key, value, type(value), SqlOperator.EQ);
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
    public static KeyMatcher neq(String key, Object value) {
        return new KeyMatcher(key, value, type(value), SqlOperator.NEQ);
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
    public static KeyMatcher gt(String key, Object value) {
        return new KeyMatcher(key, value, type(value), SqlOperator.GT);
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
    public static KeyMatcher egt(String key, Object value) {
        return new KeyMatcher(key, value, type(value), SqlOperator.EGT);
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
    public static KeyMatcher lt(String key, Object value) {
        return new KeyMatcher(key, value, type(value), SqlOperator.LT);
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
    public static KeyMatcher elt(String key, Object value) {
        return new KeyMatcher(key, value, type(value), SqlOperator.ELT);
    }

    public static KeyMatcher like(String key, String value) {
        return new KeyMatcher(key, value, JavaValueTypes.STRING, SqlOperator.LIKE);
    }

    public static KeyMatcher likeLeft(String key, String value) {
        return new KeyMatcher(key, value, JavaValueTypes.STRING, SqlOperator.LIKE_LEFT);
    }

    public static KeyMatcher likeRight(String key, String value) {
        return new KeyMatcher(key, value, JavaValueTypes.STRING, SqlOperator.LIKE_RIGHT);
    }

    public static KeyMatcher strings(String key, Collection<String> value) {
        return new KeyMatcher(key, value, JavaValueTypes.STRING, SqlOperator.IN);
    }

    public static KeyMatcher longs(String key, Collection<Long> value) {
        return new KeyMatcher(key, value, JavaValueTypes.LONG, SqlOperator.IN);
    }

    public static KeyMatcher integers(String key, Collection<Integer> value) {
        return new KeyMatcher(key, value, JavaValueTypes.INTEGER, SqlOperator.IN);
    }

    private KeyMatcher(String key, Object value, String type, SqlOperator operator) {
        this.key = key;
        this.value = value;
        this.type = type;
        this.operator = operator;
    }

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

}
