package com.bytehonor.sdk.framework.lang.query;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.framework.lang.constant.HttpConstants;
import com.bytehonor.sdk.framework.lang.constant.QueryLogic;
import com.bytehonor.sdk.framework.lang.constant.SqlOperator;
import com.bytehonor.sdk.framework.lang.core.field.FieldNameKit;
import com.bytehonor.sdk.framework.lang.function.ClassGetter;
import com.bytehonor.sdk.framework.lang.function.Getters;
import com.bytehonor.sdk.framework.lang.function.getter.GetBoolean;
import com.bytehonor.sdk.framework.lang.function.getter.GetDouble;
import com.bytehonor.sdk.framework.lang.function.getter.GetInteger;
import com.bytehonor.sdk.framework.lang.function.getter.GetLong;
import com.bytehonor.sdk.framework.lang.function.getter.GetString;

/**
 * 查询条件 DSL 入口：组合分页、过滤（{@link QueryFilter}）、排序（{@link QueryOrder}）与 AND/OR 逻辑，支持方法引用解析字段名。
 *
 * @author lijianqiang
 *
 */
public final class QueryCondition {

    private static final int LIMIT_DEF = HttpConstants.LIMIT_DEF;

    private static final int LIMIT_NON = HttpConstants.LIMIT_NON;

    private static final ConcurrentHashMap<String, String> KEY_CACHE = new ConcurrentHashMap<String, String>();

    private final QueryLogic logic;

    private final QueryPager pager;

    private final QueryFilter filter;

    private final QueryOrder order;

    private QueryCondition(QueryLogic logic, QueryPager pager) {
        Objects.requireNonNull(logic, "logic");
        Objects.requireNonNull(pager, "pager");

        this.logic = logic;
        this.pager = pager;
        this.filter = QueryFilter.plain();
        this.order = QueryOrder.plain();
    }

    public static QueryCondition one() {
        return and(0, 1);
    }

    public static QueryCondition and() {
        return and(0, LIMIT_DEF);
    }

    public static QueryCondition all() {
        return and(0, LIMIT_NON);
    }

    public static QueryCondition and(int offset, int limit) {
        return create(QueryLogic.AND, QueryPager.of(offset, limit));
    }

    public static QueryCondition or() {
        return or(0, LIMIT_DEF);
    }

    public static QueryCondition or(int offset, int limit) {
        return create(QueryLogic.OR, QueryPager.of(offset, limit));
    }

    public static QueryCondition create(QueryLogic logic, QueryPager pager) {
        return new QueryCondition(logic, pager);
    }

    public QueryCondition filter(QueryFilterColumn column) {
        this.filter.with(column);
        return this;
    }

    public QueryCondition filters(List<QueryFilterColumn> list) {
        if (CollectionUtils.isEmpty(list)) {
            return this;
        }
        for (QueryFilterColumn item : list) {
            filter(item);
        }
        return this;
    }

    /**
     * 等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition eq(GetString<T> getter, String value) {
        return append(getter, value, QueryFilterColumn::eq);
    }

    /**
     * 等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition eq(GetLong<T> getter, Long value) {
        return append(getter, value, QueryFilterColumn::eq);
    }

    /**
     * 等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition eq(GetInteger<T> getter, Integer value) {
        return append(getter, value, QueryFilterColumn::eq);
    }

    /**
     * 等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition eq(GetBoolean<T> getter, Boolean value) {
        return append(getter, value, QueryFilterColumn::eq);
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition neq(GetString<T> getter, String value) {
        return append(getter, value, QueryFilterColumn::neq);
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition neq(GetLong<T> getter, Long value) {
        return append(getter, value, QueryFilterColumn::neq);
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition neq(GetInteger<T> getter, Integer value) {
        return append(getter, value, QueryFilterColumn::neq);
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition neq(GetBoolean<T> getter, Boolean value) {
        return append(getter, value, QueryFilterColumn::neq);
    }

    /**
     * 大于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition gt(GetLong<T> getter, Long value) {
        return append(getter, value, QueryFilterColumn::gt);
    }

    /**
     * 大于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition gt(GetInteger<T> getter, Integer value) {
        return append(getter, value, QueryFilterColumn::gt);
    }

    /**
     * 大于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition gt(GetDouble<T> getter, Double value) {
        return append(getter, value, QueryFilterColumn::gt);
    }

    /**
     * 大于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition egt(GetLong<T> getter, Long value) {
        return append(getter, value, QueryFilterColumn::egt);
    }

    /**
     * 大于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition egt(GetInteger<T> getter, Integer value) {
        return append(getter, value, QueryFilterColumn::egt);
    }

    /**
     * 小于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition lt(GetLong<T> getter, Long value) {
        return append(getter, value, QueryFilterColumn::lt);
    }

    /**
     * 小于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition lt(GetInteger<T> getter, Integer value) {
        return append(getter, value, QueryFilterColumn::lt);
    }

    /**
     * 小于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition lt(GetDouble<T> getter, Double value) {
        return append(getter, value, QueryFilterColumn::lt);
    }

    /**
     * 小于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition elt(GetLong<T> getter, Long value) {
        return append(getter, value, QueryFilterColumn::elt);
    }

    /**
     * 小于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition elt(GetInteger<T> getter, Integer value) {
        return append(getter, value, QueryFilterColumn::elt);
    }

    public <T> QueryCondition like(GetString<T> getter, String value) {
        return append(getter, value, QueryFilterColumn::like);
    }

    public <T> QueryCondition likeLeft(GetString<T> getter, String value) {
        return append(getter, value, QueryFilterColumn::likeLeft);
    }

    public <T> QueryCondition likeRight(GetString<T> getter, String value) {
        return append(getter, value, QueryFilterColumn::likeRight);
    }

    public <T> QueryCondition in(GetString<T> getter, Collection<String> value) {
        return inValues(getter, value, String.class);
    }

    public <T> QueryCondition in(GetLong<T> getter, Collection<Long> value) {
        return inValues(getter, value, Long.class);
    }

    public <T> QueryCondition in(GetInteger<T> getter, Collection<Integer> value) {
        return inValues(getter, value, Integer.class);
    }

    public <T> QueryCondition desc(ClassGetter<T, ?> getter) {
        return orderBy(getter, true, false);
    }

    public <T> QueryCondition descIfNon(ClassGetter<T, ?> getter) {
        return orderBy(getter, true, true);
    }

    public <T> QueryCondition asc(ClassGetter<T, ?> getter) {
        return orderBy(getter, false, false);
    }

    public <T> QueryCondition ascIfNon(ClassGetter<T, ?> getter) {
        return orderBy(getter, false, true);
    }

    public QueryCondition order(QueryOrderColumn column) {
        this.order.with(column);
        return this;
    }

    public QueryCondition orders(List<QueryOrderColumn> list) {
        if (CollectionUtils.isEmpty(list)) {
            return this;
        }
        for (QueryOrderColumn item : list) {
            order(item);
        }
        return this;
    }

    public boolean canOrder() {
        return order.canOrder();
    }

    public int offset() {
        return this.pager.getOffset();
    }

    public QueryCondition offset(int offset) {
        this.pager.setOffset(offset);
        return this;
    }

    public QueryCondition limit(int limit) {
        this.pager.setLimit(limit);
        return this;
    }

    public int limit() {
        return this.pager.getLimit();
    }

    public QueryCondition counted(boolean counted) {
        this.pager.setCounted(counted);
        return this;
    }

    public boolean canCount() {
        return this.pager.canCount();
    }

    public QueryPager getPager() {
        return pager;
    }

    public QueryOrder getOrder() {
        return order;
    }

    public QueryLogic getLogic() {
        return logic;
    }

    public QueryFilter getFilter() {
        return filter;
    }

    public <T> boolean has(ClassGetter<T, ?> getter) {
        return filter.contains(key(getter));
    }

    public boolean canFilter() {
        return filter.canFilter();
    }

    public <T> String getString(GetString<T> getter, String operator) {
        return filter.getString(key(getter), operator);
    }

    public <T> String getStringEq(GetString<T> getter) {
        return getString(getter, SqlOperator.EQ.getKey());
    }

    public static <T> String key(ClassGetter<T, ?> getter) {
        Objects.requireNonNull(getter, "getter");
        String lambdaClassName = getter.getClass().getName();
        return KEY_CACHE.computeIfAbsent(lambdaClassName, ignored -> FieldNameKit.underline(Getters.field(getter)));
    }

    private <T> QueryCondition orderBy(ClassGetter<T, ?> getter, boolean desc, boolean onlyIfEmpty) {
        if (onlyIfEmpty && canOrder()) {
            return this;
        }
        String key = key(getter);
        if (desc) {
            this.order.desc(key);
        } else {
            this.order.asc(key);
        }
        return this;
    }

    private <T, V> QueryCondition append(ClassGetter<T, ?> getter, V value,
            BiFunction<String, V, QueryFilterColumn> creator) {
        return filter(creator.apply(key(getter), value));
    }

    private <T, V> QueryCondition inValues(ClassGetter<T, ?> getter, Collection<V> values, Class<V> type) {
        return filter(QueryFilterColumn.in(key(getter), values, type));
    }

}
