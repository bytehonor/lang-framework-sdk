package com.bytehonor.sdk.lang.spring.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import com.bytehonor.sdk.lang.spring.constant.HttpConstants;
import com.bytehonor.sdk.lang.spring.constant.QueryLogic;
import com.bytehonor.sdk.lang.spring.function.ClassGetter;
import com.bytehonor.sdk.lang.spring.function.Getters;
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

    private final QueryPage page;

    private QueryOrder order;

    private final List<KeyMatcher> matchers;

    private QueryCondition(QueryLogic logic, QueryPage page) {
        this.logic = logic;
        this.page = page;
        this.order = null;
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
        return create(QueryLogic.AND, QueryPage.of(offset, limit));
    }

    public static QueryCondition or() {
        return or(0, LIMIT_DEF);
    }

    public static QueryCondition or(int offset, int limit) {
        return create(QueryLogic.OR, QueryPage.of(offset, limit));
    }

    public static QueryCondition create(QueryLogic logic, QueryPage page) {
        Objects.requireNonNull(logic, "logic");
        Objects.requireNonNull(page, "page");

        return new QueryCondition(logic, page);
    }

    private QueryCondition doSafeAdd(KeyMatcher matcher) {
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
    public <T> QueryCondition eq(ClassGetter<T, ?> getter, String value) {
        return this.doSafeAdd(KeyMatcher.eq(Getters.field(getter), value));
    }

    /**
     * 等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition eq(ClassGetter<T, ?> getter, Long value) {
        return this.doSafeAdd(KeyMatcher.eq(Getters.field(getter), value));
    }

    /**
     * 等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition eq(ClassGetter<T, ?> getter, Integer value) {
        return this.doSafeAdd(KeyMatcher.eq(Getters.field(getter), value));
    }

    /**
     * 等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition eq(ClassGetter<T, ?> getter, Boolean value) {
        return this.doSafeAdd(KeyMatcher.eq(Getters.field(getter), value));
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition neq(ClassGetter<T, ?> getter, String value) {
        return this.doSafeAdd(KeyMatcher.neq(Getters.field(getter), value));
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition neq(ClassGetter<T, ?> getter, Long value) {
        return this.doSafeAdd(KeyMatcher.neq(Getters.field(getter), value));
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition neq(ClassGetter<T, ?> getter, Integer value) {
        return this.doSafeAdd(KeyMatcher.neq(Getters.field(getter), value));
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition neq(ClassGetter<T, ?> getter, Boolean value) {
        return this.doSafeAdd(KeyMatcher.neq(Getters.field(getter), value));
    }

    /**
     * 大于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition gt(ClassGetter<T, ?> getter, Long value) {
        return this.doSafeAdd(KeyMatcher.gt(Getters.field(getter), value));
    }

    /**
     * 大于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition gt(ClassGetter<T, ?> getter, Integer value) {
        return this.doSafeAdd(KeyMatcher.gt(Getters.field(getter), value));
    }

    /**
     * 大于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition egt(ClassGetter<T, ?> getter, Long value) {
        return this.doSafeAdd(KeyMatcher.egt(Getters.field(getter), value));
    }

    /**
     * 大于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition egt(ClassGetter<T, ?> getter, Integer value) {
        return this.doSafeAdd(KeyMatcher.egt(Getters.field(getter), value));
    }

    /**
     * 小于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition lt(ClassGetter<T, ?> getter, Long value) {
        return this.doSafeAdd(KeyMatcher.lt(Getters.field(getter), value));
    }

    /**
     * 小于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition lt(ClassGetter<T, ?> getter, Integer value) {
        return this.doSafeAdd(KeyMatcher.lt(Getters.field(getter), value));
    }

    /**
     * 小于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition elt(ClassGetter<T, ?> getter, Long value) {
        return this.doSafeAdd(KeyMatcher.elt(Getters.field(getter), value));
    }

    /**
     * 小于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition elt(ClassGetter<T, ?> getter, Integer value) {
        return this.doSafeAdd(KeyMatcher.elt(Getters.field(getter), value));
    }

    public <T> QueryCondition like(ClassGetter<T, ?> getter, String value) {
        return this.doSafeAdd(KeyMatcher.like(Getters.field(getter), value));
    }

    public <T> QueryCondition likeLeft(ClassGetter<T, ?> getter, String value) {
        return this.doSafeAdd(KeyMatcher.likeLeft(Getters.field(getter), value));
    }

    public <T> QueryCondition likeRight(ClassGetter<T, ?> getter, String value) {
        return this.doSafeAdd(KeyMatcher.likeRight(Getters.field(getter), value));
    }

    public <T> QueryCondition strings(ClassGetter<T, ?> getter, Collection<String> value) {
        return this.doSafeAdd(KeyMatcher.strings(Getters.field(getter), value));
    }

    public <T> QueryCondition longs(ClassGetter<T, ?> getter, Collection<Long> value) {
        return this.doSafeAdd(KeyMatcher.longs(Getters.field(getter), value));
    }

    public <T> QueryCondition integers(ClassGetter<T, ?> getter, Collection<Integer> value) {
        return this.doSafeAdd(KeyMatcher.integers(Getters.field(getter), value));
    }

    public <T> QueryCondition desc(ClassGetter<T, ?> getter) {
        this.order = QueryOrder.descOf(Getters.field(getter));
        return this;
    }

    public <T> QueryCondition asc(ClassGetter<T, ?> getter) {
        this.order = QueryOrder.ascOf(Getters.field(getter));
        return this;
    }

    public void offset(int offset) {
        this.page.setOffset(offset);
    }

    public void limit(int limit) {
        this.page.setLimit(limit);
    }

    public QueryPage getPage() {
        return page;
    }

    public QueryOrder getOrder() {
        return order;
    }

    public void setOrder(QueryOrder order) {
        this.order = order;
    }

    public QueryLogic getLogic() {
        return logic;
    }

    public List<KeyMatcher> getMatchers() {
        return matchers;
    }

}
