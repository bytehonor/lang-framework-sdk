package com.bytehonor.sdk.framework.lang.query;

import java.util.Collection;
import java.util.Objects;

import com.bytehonor.sdk.framework.lang.constant.JavaValueTypes;
import com.bytehonor.sdk.framework.lang.constant.SqlOperator;
import com.bytehonor.sdk.framework.lang.core.field.FieldNameKit;
import com.bytehonor.sdk.framework.lang.exception.SpringLangException;
import com.bytehonor.sdk.framework.lang.string.StringKit;

/**
 * 单条查询过滤条件：列名（统一转下划线）、值、Java 类型标识与 {@link SqlOperator}。
 *
 * @author lijianqiang
 */
public class QueryFilterColumn {

    private static final String LIKE_ONLY_SUPPORT_STRING = "LIKE operator only supports String type";

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
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(type, "type");
        Objects.requireNonNull(operator, "operator");
        validateOperatorType(key, type, operator);
        return new QueryFilterColumn(key, value, type, operator);
    }

    private static void validateOperatorType(String key, String type, SqlOperator operator) {
        if (isLikeOperator(operator) && !JavaValueTypes.STRING.equals(type)) {
            throw new SpringLangException(key + " " + LIKE_ONLY_SUPPORT_STRING + ", but was: " + type);
        }
    }

    private static boolean isLikeOperator(SqlOperator operator) {
        return SqlOperator.LIKE.equals(operator) || SqlOperator.LIKE_LEFT.equals(operator)
                || SqlOperator.LIKE_RIGHT.equals(operator);
    }

    /**
     * 字符串空值也会被采纳
     * 非空集合才会被采纳（空集合会被过滤，避免产生无效 IN 条件）
     * 
     * @param filter
     * @return true 表示该条件有效（key/operator/value 均有效，且 Collection 值非空）
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
        if (filter.getValue() == null) {
            return false;
        }
        if (filter.getValue() instanceof Collection<?>) {
            return !((Collection<?>) filter.getValue()).isEmpty();
        }
        return true;
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
        return ofString(key, value, SqlOperator.EQ);
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
        return ofLong(key, value, SqlOperator.EQ);
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
        return ofInteger(key, value, SqlOperator.EQ);
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
        return ofBoolean(key, value, SqlOperator.EQ);
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
        return ofString(key, value, SqlOperator.NEQ);
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
        return ofLong(key, value, SqlOperator.NEQ);
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
        return ofInteger(key, value, SqlOperator.NEQ);
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
        return ofBoolean(key, value, SqlOperator.NEQ);
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
        return ofLong(key, value, SqlOperator.GT);
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
        return ofInteger(key, value, SqlOperator.GT);
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
        return ofDouble(key, value, SqlOperator.GT);
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
        return ofLong(key, value, SqlOperator.EGT);
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
        return ofInteger(key, value, SqlOperator.EGT);
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
        return ofLong(key, value, SqlOperator.LT);
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
        return ofInteger(key, value, SqlOperator.LT);
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
        return ofDouble(key, value, SqlOperator.LT);
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
        return ofLong(key, value, SqlOperator.ELT);
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
        return ofInteger(key, value, SqlOperator.ELT);
    }

    public static QueryFilterColumn like(String key, String value) {
        return ofString(key, value, SqlOperator.LIKE);
    }

    public static QueryFilterColumn likeLeft(String key, String value) {
        return ofString(key, value, SqlOperator.LIKE_LEFT);
    }

    public static QueryFilterColumn likeRight(String key, String value) {
        return ofString(key, value, SqlOperator.LIKE_RIGHT);
    }

    public static <T> QueryFilterColumn in(String key, Collection<T> values, Class<T> type) {
        Objects.requireNonNull(type, "type");
        return ofType(key, values, type.getName(), SqlOperator.IN);
    }

    public static <T> QueryFilterColumn in(String key, Collection<T> values, String type) {
        return ofType(key, values, type, SqlOperator.IN);
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
        return QueryHelper.unique(key, operator.getKey());
    }

    private static QueryFilterColumn ofString(String key, String value, SqlOperator operator) {
        return ofType(key, value, JavaValueTypes.STRING, operator);
    }

    private static QueryFilterColumn ofLong(String key, Long value, SqlOperator operator) {
        return ofType(key, value, JavaValueTypes.LONG, operator);
    }

    private static QueryFilterColumn ofInteger(String key, Integer value, SqlOperator operator) {
        return ofType(key, value, JavaValueTypes.INTEGER, operator);
    }

    private static QueryFilterColumn ofBoolean(String key, Boolean value, SqlOperator operator) {
        return ofType(key, value, JavaValueTypes.BOOLEAN, operator);
    }

    private static QueryFilterColumn ofDouble(String key, Double value, SqlOperator operator) {
        return ofType(key, value, JavaValueTypes.DOUBLE, operator);
    }

    private static QueryFilterColumn ofType(String key, Object value, String type, SqlOperator operator) {
        return of(key, value, type, operator);
    }
}
