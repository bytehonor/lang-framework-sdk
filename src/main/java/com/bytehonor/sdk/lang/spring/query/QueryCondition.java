package com.bytehonor.sdk.lang.spring.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.lang.spring.constant.HttpConstants;
import com.bytehonor.sdk.lang.spring.constant.QueryLogic;
import com.bytehonor.sdk.lang.spring.constant.SqlOperator;
import com.bytehonor.sdk.lang.spring.function.ClassGetter;
import com.bytehonor.sdk.lang.spring.function.Getters;
import com.bytehonor.sdk.lang.spring.function.getter.GetBoolean;
import com.bytehonor.sdk.lang.spring.function.getter.GetDouble;
import com.bytehonor.sdk.lang.spring.function.getter.GetInteger;
import com.bytehonor.sdk.lang.spring.function.getter.GetLong;
import com.bytehonor.sdk.lang.spring.function.getter.GetString;
import com.bytehonor.sdk.lang.spring.meta.MetaGetter;
import com.bytehonor.sdk.lang.spring.query.QueryOrder.QueryOrderColumn;

/**
 * 
 * @author lijianqiang
 *
 */
public final class QueryCondition {

    private static final int LIMIT_DEF = HttpConstants.LIMIT_DEF;

    private static final int LIMIT_NON = HttpConstants.LIMIT_NON;

    private final QueryLogic logic;

    private final QueryPager pager;

    private final QueryOrder order;

    /**
     * unique = key + operator, key是下划线风格, 且要拼接操作符, 防止 key gt 同时 key lt 的条件
     */
    private final Map<String, QueryFilter> filters;

    private final Set<String> keys;

    private QueryCondition(QueryLogic logic, QueryPager pager) {
        Objects.requireNonNull(logic, "logic");
        Objects.requireNonNull(pager, "pager");

        this.logic = logic;
        this.pager = pager;
        this.order = QueryOrder.non();
        this.filters = new HashMap<String, QueryFilter>();
        this.keys = new HashSet<String>();
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

    public QueryCondition add(QueryFilter filter) {
        if (QueryFilter.accept(filter)) {
            this.filters.put(filter.unique(), filter); // 必须用unique
            this.keys.add(filter.getKey());
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
        return this.add(QueryFilter.eq(key(getter), value));
    }

    /**
     * 等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition eq(GetLong<T> getter, Long value) {
        return this.add(QueryFilter.eq(key(getter), value));
    }

    /**
     * 等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition eq(GetInteger<T> getter, Integer value) {
        return this.add(QueryFilter.eq(key(getter), value));
    }

    /**
     * 等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition eq(GetBoolean<T> getter, Boolean value) {
        return this.add(QueryFilter.eq(key(getter), value));
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition neq(GetString<T> getter, String value) {
        return this.add(QueryFilter.neq(key(getter), value));
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition neq(GetLong<T> getter, Long value) {
        return this.add(QueryFilter.neq(key(getter), value));
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition neq(GetInteger<T> getter, Integer value) {
        return this.add(QueryFilter.neq(key(getter), value));
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition neq(GetBoolean<T> getter, Boolean value) {
        return this.add(QueryFilter.neq(key(getter), value));
    }

    /**
     * 大于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition gt(GetLong<T> getter, Long value) {
        return this.add(QueryFilter.gt(key(getter), value));
    }

    /**
     * 大于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition gt(GetInteger<T> getter, Integer value) {
        return this.add(QueryFilter.gt(key(getter), value));
    }

    /**
     * 大于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition gt(GetDouble<T> getter, Double value) {
        return this.add(QueryFilter.gt(key(getter), value));
    }

    /**
     * 大于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition egt(GetLong<T> getter, Long value) {
        return this.add(QueryFilter.egt(key(getter), value));
    }

    /**
     * 大于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition egt(GetInteger<T> getter, Integer value) {
        return this.add(QueryFilter.egt(key(getter), value));
    }

    /**
     * 小于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition lt(GetLong<T> getter, Long value) {
        return this.add(QueryFilter.lt(key(getter), value));
    }

    /**
     * 小于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition lt(GetInteger<T> getter, Integer value) {
        return this.add(QueryFilter.lt(key(getter), value));
    }

    /**
     * 小于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition lt(GetDouble<T> getter, Double value) {
        return this.add(QueryFilter.lt(key(getter), value));
    }

    /**
     * 小于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition elt(GetLong<T> getter, Long value) {
        return this.add(QueryFilter.elt(key(getter), value));
    }

    /**
     * 小于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition elt(GetInteger<T> getter, Integer value) {
        return this.add(QueryFilter.elt(key(getter), value));
    }

    public <T> QueryCondition like(GetString<T> getter, String value) {
        return this.add(QueryFilter.like(key(getter), value));
    }

    public <T> QueryCondition likeLeft(GetString<T> getter, String value) {
        return this.add(QueryFilter.likeLeft(key(getter), value));
    }

    public <T> QueryCondition likeRight(GetString<T> getter, String value) {
        return this.add(QueryFilter.likeRight(key(getter), value));
    }

    public <T> QueryCondition in(GetString<T> getter, Collection<String> value) {
        return this.add(QueryFilter.in(key(getter), value, String.class));
    }

    public <T> QueryCondition in(GetLong<T> getter, Collection<Long> value) {
        return this.add(QueryFilter.in(key(getter), value, Long.class));
    }

    public <T> QueryCondition in(GetInteger<T> getter, Collection<Integer> value) {
        return this.add(QueryFilter.in(key(getter), value, Integer.class));
    }

    public <T> QueryCondition desc(ClassGetter<T, ?> getter) {
        this.order.desc(key(getter));
        return this;
    }

    public <T> QueryCondition descIfNon(ClassGetter<T, ?> getter) {
        if (canOrder() == false) {
            this.order.desc(key(getter));
        }
        return this;
    }

    public <T> QueryCondition asc(ClassGetter<T, ?> getter) {
        this.order.asc(key(getter));
        return this;
    }

    public <T> QueryCondition ascIfNon(ClassGetter<T, ?> getter) {
        if (canOrder() == false) {
            this.order.asc(key(getter));
        }
        return this;
    }

    public <T> QueryCondition order(QueryOrderColumn column) {
        this.order.with(column);
        return this;
    }

    public <T> QueryCondition orders(List<QueryOrderColumn> list) {
        if (CollectionUtils.isEmpty(list)) {
            return this;
        }
        for (QueryOrderColumn item : list) {
            order(item);
        }
        return this;
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

    public List<QueryFilter> listFilters() {
        List<QueryFilter> list = new ArrayList<QueryFilter>();
        for (Entry<String, QueryFilter> item : filters.entrySet()) {
            list.add(item.getValue());
        }
        return list;
    }

    public <T> boolean has(ClassGetter<T, ?> getter) {
        String key = key(getter);
        return keys.contains(key);
    }

    public <T> String getString(GetString<T> getter, String operator) {
        String key = key(getter);
        if (keys.contains(key) == false) {
            return null;
        }

        String unique = QueryFilter.unique(key, operator);
        QueryFilter filter = filters.get(unique);
        return filter != null ? filter.getValue().toString() : null;
    }

    public <T> String getStringEq(GetString<T> getter) {
        return getString(getter, SqlOperator.EQ.getKey());
    }

    public static <T> String key(ClassGetter<T, ?> getter) {
        return MetaGetter.underline(Getters.field(getter));
    }

    public boolean canOrder() {
        return order.canOrder();
    }

}
