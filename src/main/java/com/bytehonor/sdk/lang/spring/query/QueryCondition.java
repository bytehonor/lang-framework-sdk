package com.bytehonor.sdk.lang.spring.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import com.bytehonor.sdk.lang.spring.constant.HttpConstants;
import com.bytehonor.sdk.lang.spring.constant.QueryLogic;
import com.bytehonor.sdk.lang.spring.function.ClassGetter;
import com.bytehonor.sdk.lang.spring.function.Getters;
import com.bytehonor.sdk.lang.spring.function.getter.GetBoolean;
import com.bytehonor.sdk.lang.spring.function.getter.GetDouble;
import com.bytehonor.sdk.lang.spring.function.getter.GetInteger;
import com.bytehonor.sdk.lang.spring.function.getter.GetLong;
import com.bytehonor.sdk.lang.spring.function.getter.GetString;
import com.bytehonor.sdk.lang.spring.meta.MetaGetter;
import com.bytehonor.sdk.lang.spring.string.SpringString;

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
     * key是下划线风格
     */
    private final Map<String, QueryFilter> filters;

    private QueryCondition(QueryLogic logic, QueryPager pager) {
        Objects.requireNonNull(logic, "logic");
        Objects.requireNonNull(pager, "pager");

        this.logic = logic;
        this.pager = pager;
        this.order = QueryOrder.non();
        this.filters = new HashMap<String, QueryFilter>();
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
            this.filters.put(filter.getKey(), filter);
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
        if (sorted() == false) {
            this.order.desc(key(getter));
        }
        return this;
    }

    public <T> QueryCondition asc(ClassGetter<T, ?> getter) {
        this.order.asc(key(getter));
        return this;
    }

    public <T> QueryCondition ascIfNon(ClassGetter<T, ?> getter) {
        if (sorted() == false) {
            this.order.asc(key(getter));
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

    public boolean counted() {
        return this.pager.isCounted();
    }

    public QueryCondition order(QueryOrder order) {
        if (order != null && SpringString.isEmpty(order.getKey()) == false) {
            this.order.setKey(order.getKey());
            this.order.setDesc(order.isDesc());
        }
        return this;
    }

    public QueryCondition orderIfNon(QueryOrder order) {
        if (sorted()) {
            return this;
        }
        return order(order);
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
        return filters.containsKey(key);
    }

    public <T> String getString(GetString<T> getter) {
        String key = key(getter);
        QueryFilter filter = filters.get(key);
        return filter != null ? filter.getValue().toString() : null;
    }

    public static <T> String key(ClassGetter<T, ?> getter) {
        return MetaGetter.underline(Getters.field(getter));
    }

    public boolean sorted() {
        return SpringString.isEmpty(order.getKey()) == false;
    }

}
