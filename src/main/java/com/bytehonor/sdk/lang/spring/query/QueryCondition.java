package com.bytehonor.sdk.lang.spring.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import com.bytehonor.sdk.lang.spring.constant.HttpConstants;
import com.bytehonor.sdk.lang.spring.constant.QueryLogic;
import com.bytehonor.sdk.lang.spring.function.ClassGetter;
import com.bytehonor.sdk.lang.spring.function.Getters;
import com.bytehonor.sdk.lang.spring.function.getter.GetBoolean;
import com.bytehonor.sdk.lang.spring.function.getter.GetInteger;
import com.bytehonor.sdk.lang.spring.function.getter.GetLong;
import com.bytehonor.sdk.lang.spring.function.getter.GetString;
import com.bytehonor.sdk.lang.spring.match.KeyMatcher;

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

    private final List<KeyMatcher> matchers;

    private QueryCondition(QueryLogic logic, QueryPager pager) {
        this.logic = logic;
        this.pager = pager;
        this.order = QueryOrder.non();
        this.matchers = new ArrayList<KeyMatcher>();
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

    private static QueryCondition create(QueryLogic logic, QueryPager page) {
        Objects.requireNonNull(logic, "logic");
        Objects.requireNonNull(page, "page");

        return new QueryCondition(logic, page);
    }

    public QueryCondition add(KeyMatcher matcher) {
        if (KeyMatcher.accept(matcher)) {
            this.matchers.add(matcher);
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
        return this.add(KeyMatcher.eq(Getters.field(getter), value));
    }

    /**
     * 等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition eq(GetLong<T> getter, Long value) {
        return this.add(KeyMatcher.eq(Getters.field(getter), value));
    }

    /**
     * 等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition eq(GetInteger<T> getter, Integer value) {
        return this.add(KeyMatcher.eq(Getters.field(getter), value));
    }

    /**
     * 等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition eq(GetBoolean<T> getter, Boolean value) {
        return this.add(KeyMatcher.eq(Getters.field(getter), value));
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition neq(GetString<T> getter, String value) {
        return this.add(KeyMatcher.neq(Getters.field(getter), value));
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition neq(GetLong<T> getter, Long value) {
        return this.add(KeyMatcher.neq(Getters.field(getter), value));
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition neq(GetInteger<T> getter, Integer value) {
        return this.add(KeyMatcher.neq(Getters.field(getter), value));
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition neq(GetBoolean<T> getter, Boolean value) {
        return this.add(KeyMatcher.neq(Getters.field(getter), value));
    }

    /**
     * 大于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition gt(GetLong<T> getter, Long value) {
        return this.add(KeyMatcher.gt(Getters.field(getter), value));
    }

    /**
     * 大于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition gt(GetInteger<T> getter, Integer value) {
        return this.add(KeyMatcher.gt(Getters.field(getter), value));
    }

    /**
     * 大于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition egt(GetLong<T> getter, Long value) {
        return this.add(KeyMatcher.egt(Getters.field(getter), value));
    }

    /**
     * 大于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition egt(GetInteger<T> getter, Integer value) {
        return this.add(KeyMatcher.egt(Getters.field(getter), value));
    }

    /**
     * 小于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition lt(GetLong<T> getter, Long value) {
        return this.add(KeyMatcher.lt(Getters.field(getter), value));
    }

    /**
     * 小于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition lt(GetInteger<T> getter, Integer value) {
        return this.add(KeyMatcher.lt(Getters.field(getter), value));
    }

    /**
     * 小于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition elt(GetLong<T> getter, Long value) {
        return this.add(KeyMatcher.elt(Getters.field(getter), value));
    }

    /**
     * 小于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition elt(GetInteger<T> getter, Integer value) {
        return this.add(KeyMatcher.elt(Getters.field(getter), value));
    }

    public <T> QueryCondition like(GetString<T> getter, String value) {
        return this.add(KeyMatcher.like(Getters.field(getter), value));
    }

    public <T> QueryCondition likeLeft(GetString<T> getter, String value) {
        return this.add(KeyMatcher.likeLeft(Getters.field(getter), value));
    }

    public <T> QueryCondition likeRight(GetString<T> getter, String value) {
        return this.add(KeyMatcher.likeRight(Getters.field(getter), value));
    }

    public <T> QueryCondition in(GetString<T> getter, Collection<String> value) {
        return this.add(KeyMatcher.in(Getters.field(getter), value, String.class));
    }

    public <T> QueryCondition in(GetLong<T> getter, Collection<Long> value) {
        return this.add(KeyMatcher.in(Getters.field(getter), value, Long.class));
    }

    public <T> QueryCondition in(GetInteger<T> getter, Collection<Integer> value) {
        return this.add(KeyMatcher.in(Getters.field(getter), value, Integer.class));
    }

    public <T> QueryCondition desc(ClassGetter<T, ?> getter) {
        this.order.desc(Getters.field(getter));
        return this;
    }

    public <T> QueryCondition asc(ClassGetter<T, ?> getter) {
        this.order.asc(Getters.field(getter));
        return this;
    }

    public void offset(int offset) {
        this.pager.setOffset(offset);
    }

    public void limit(int limit) {
        this.pager.setLimit(limit);
    }

    public void count(boolean counted) {
        this.pager.setCounted(counted);
    }

    public boolean isCounted() {
        return this.pager.isCounted();
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

    public List<KeyMatcher> getMatchers() {
        return matchers;
    }

}
