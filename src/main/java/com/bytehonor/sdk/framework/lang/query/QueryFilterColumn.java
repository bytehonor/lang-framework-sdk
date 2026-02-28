package com.bytehonor.sdk.framework.lang.query;

import java.util.Collection;

import com.bytehonor.sdk.framework.lang.constant.JavaValueTypes;
import com.bytehonor.sdk.framework.lang.constant.SqlOperator;
import com.bytehonor.sdk.framework.lang.core.field.FieldNameKit;
import com.bytehonor.sdk.framework.lang.exception.SpringLangException;
import com.bytehonor.sdk.framework.lang.string.StringKit;

public class QueryFilterColumn {

    /**
     * 忽略驼峰及下划线风格, 统一转成了下划线
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

    private QueryFilterColumn(String key, Object value, String type, SqlOperator operator) {
        this.key = FieldNameKit.underline(key);
        this.value = value;
        this.type = type;
        this.operator = operator;
    }

    public static QueryFilterColumn non() {
        return new QueryFilterColumn("", "", JavaValueTypes.STRING, SqlOperator.EQ);
    }

    public static QueryFilterColumn of(String key, Object value, String type, SqlOperator operator) {
        if (SqlOperator.LIKE.equals(operator) && JavaValueTypes.STRING.equals(type) == false) {
            throw new SpringLangException(key + " cannt be like");
        }
        if (SqlOperator.LIKE_LEFT.equals(operator) && JavaValueTypes.STRING.equals(type) == false) {
            throw new SpringLangException(key + " cannt be like");
        }
        if (SqlOperator.LIKE_RIGHT.equals(operator) && JavaValueTypes.STRING.equals(type) == false) {
            throw new SpringLangException(key + " cannt be like");
        }
        return new QueryFilterColumn(key, value, type, operator);
    }

    /**
     * 字符串空值也会被采纳
     * 
     * @param filter
     * @return
     */
    public static boolean accept(QueryFilterColumn filter) {
        if (filter == null) {
            return false;
        }
        if (filter.getOperator() == null) {
            return false;
        }
        if (StringKit.isEmpty(filter.getKey())) {
            return false;
        }
        return filter.getValue() != null;
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
    public static QueryFilterColumn eq(String key, String value) {
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
    public static QueryFilterColumn eq(String key, Long value) {
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
    public static QueryFilterColumn eq(String key, Integer value) {
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
    public static QueryFilterColumn eq(String key, Boolean value) {
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
    public static QueryFilterColumn neq(String key, String value) {
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
    public static QueryFilterColumn neq(String key, Long value) {
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
    public static QueryFilterColumn neq(String key, Integer value) {
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
    public static QueryFilterColumn neq(String key, Boolean value) {
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
    public static QueryFilterColumn gt(String key, Long value) {
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
    public static QueryFilterColumn gt(String key, Integer value) {
        return of(key, value, JavaValueTypes.INTEGER, SqlOperator.GT);
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
    public static QueryFilterColumn gt(String key, Double value) {
        return of(key, value, JavaValueTypes.DOUBLE, SqlOperator.GT);
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
    public static QueryFilterColumn egt(String key, Long value) {
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
    public static QueryFilterColumn egt(String key, Integer value) {
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
    public static QueryFilterColumn lt(String key, Long value) {
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
    public static QueryFilterColumn lt(String key, Integer value) {
        return of(key, value, JavaValueTypes.INTEGER, SqlOperator.LT);
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
    public static QueryFilterColumn lt(String key, Double value) {
        return of(key, value, JavaValueTypes.DOUBLE, SqlOperator.LT);
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
    public static QueryFilterColumn elt(String key, Long value) {
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
    public static QueryFilterColumn elt(String key, Integer value) {
        return of(key, value, JavaValueTypes.INTEGER, SqlOperator.ELT);
    }

    public static QueryFilterColumn like(String key, String value) {
        return of(key, value, JavaValueTypes.STRING, SqlOperator.LIKE);
    }

    public static QueryFilterColumn likeLeft(String key, String value) {
        return of(key, value, JavaValueTypes.STRING, SqlOperator.LIKE_LEFT);
    }

    public static QueryFilterColumn likeRight(String key, String value) {
        return of(key, value, JavaValueTypes.STRING, SqlOperator.LIKE_RIGHT);
    }

    public static <T> QueryFilterColumn in(String key, Collection<T> values, Class<T> type) {
        return of(key, values, type.getName(), SqlOperator.IN);
    }

    public static <T> QueryFilterColumn in(String key, Collection<T> values, String type) {
        return of(key, values, type, SqlOperator.IN);
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

    public String unique() {
        return unique(key, operator.getKey());
    }

    public static String unique(String key, String operator) {
        return new StringBuilder().append(key).append("-").append(operator).toString();
    }
}
