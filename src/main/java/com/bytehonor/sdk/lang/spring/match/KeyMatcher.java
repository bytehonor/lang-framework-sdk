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
        return new KeyMatcher(key, value, JavaValueTypes.STRING, SqlOperator.EQ);
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
        return new KeyMatcher(key, value, JavaValueTypes.LONG, SqlOperator.EQ);
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
        return new KeyMatcher(key, value, JavaValueTypes.INTEGER, SqlOperator.EQ);
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
        return new KeyMatcher(key, value, JavaValueTypes.BOOLEAN, SqlOperator.EQ);
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
        return new KeyMatcher(key, value, JavaValueTypes.STRING, SqlOperator.NEQ);
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
        return new KeyMatcher(key, value, JavaValueTypes.LONG, SqlOperator.NEQ);
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
        return new KeyMatcher(key, value, JavaValueTypes.INTEGER, SqlOperator.NEQ);
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
        return new KeyMatcher(key, value, JavaValueTypes.BOOLEAN, SqlOperator.NEQ);
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
        return new KeyMatcher(key, value, JavaValueTypes.LONG, SqlOperator.GT);
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
        return new KeyMatcher(key, value, JavaValueTypes.INTEGER, SqlOperator.GT);
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
        return new KeyMatcher(key, value, JavaValueTypes.LONG, SqlOperator.EGT);
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
        return new KeyMatcher(key, value, JavaValueTypes.INTEGER, SqlOperator.EGT);
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
        return new KeyMatcher(key, value, JavaValueTypes.LONG, SqlOperator.LT);
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
        return new KeyMatcher(key, value, JavaValueTypes.INTEGER, SqlOperator.LT);
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
        return new KeyMatcher(key, value, JavaValueTypes.LONG, SqlOperator.ELT);
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
        return new KeyMatcher(key, value, JavaValueTypes.INTEGER, SqlOperator.ELT);
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
