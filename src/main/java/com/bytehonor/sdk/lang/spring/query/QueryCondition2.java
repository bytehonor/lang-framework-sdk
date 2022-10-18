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
public final class QueryCondition2 {

    private static final int LIMIT_DEF = HttpConstants.LIMIT_DEF;

    private static final int LIMIT_NON = HttpConstants.LIMIT_NON;

    private final QueryLogic logic;

    private final QueryPage page;

    private QueryOrder order;

    private final List<KeyMatcher> matchers;

    private QueryCondition2(QueryLogic logic, QueryPage page) {
        this.logic = logic;
        this.page = page;
        this.order = null;
        this.matchers = new ArrayList<KeyMatcher>();
    }

    public static QueryCondition2 one() {
        return and(0, 1);
    }

    public static QueryCondition2 and() {
        return and(0, LIMIT_DEF);
    }

    public static QueryCondition2 all() {
        return and(0, LIMIT_NON);
    }

    public static QueryCondition2 and(int offset, int limit) {
        return create(QueryLogic.AND, QueryPage.of(offset, limit));
    }

    public static QueryCondition2 or() {
        return or(0, LIMIT_DEF);
    }

    public static QueryCondition2 or(int offset, int limit) {
        return create(QueryLogic.OR, QueryPage.of(offset, limit));
    }

    public static QueryCondition2 create(QueryLogic logic, QueryPage page) {
        Objects.requireNonNull(logic, "logic");
        Objects.requireNonNull(page, "page");

        return new QueryCondition2(logic, page);
    }

    public QueryCondition2 add(KeyMatcher matcher) {
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
    public <T> QueryCondition2 eq(ClassGetter<T, ?> getter, String value) {
        return this.add(KeyMatcher.eq(Getters.field(getter), value));
    }

    /**
     * 等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition2 eq(ClassGetter<T, ?> getter, Long value) {
        return this.add(KeyMatcher.eq(Getters.field(getter), value));
    }

    /**
     * 等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition2 eq(ClassGetter<T, ?> getter, Integer value) {
        return this.add(KeyMatcher.eq(Getters.field(getter), value));
    }

    /**
     * 等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition2 eq(ClassGetter<T, ?> getter, Boolean value) {
        return this.add(KeyMatcher.eq(Getters.field(getter), value));
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition2 neq(ClassGetter<T, ?> getter, String value) {
        return this.add(KeyMatcher.neq(Getters.field(getter), value));
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition2 neq(ClassGetter<T, ?> getter, Long value) {
        return this.add(KeyMatcher.neq(Getters.field(getter), value));
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition2 neq(ClassGetter<T, ?> getter, Integer value) {
        return this.add(KeyMatcher.neq(Getters.field(getter), value));
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition2 neq(ClassGetter<T, ?> getter, Boolean value) {
        return this.add(KeyMatcher.neq(Getters.field(getter), value));
    }

    /**
     * 大于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition2 gt(ClassGetter<T, ?> getter, Long value) {
        return this.add(KeyMatcher.gt(Getters.field(getter), value));
    }

    /**
     * 大于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition2 gt(ClassGetter<T, ?> getter, Integer value) {
        return this.add(KeyMatcher.gt(Getters.field(getter), value));
    }

    /**
     * 大于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition2 egt(ClassGetter<T, ?> getter, Long value) {
        return this.add(KeyMatcher.egt(Getters.field(getter), value));
    }

    /**
     * 大于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition2 egt(ClassGetter<T, ?> getter, Integer value) {
        return this.add(KeyMatcher.egt(Getters.field(getter), value));
    }

    /**
     * 小于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition2 lt(ClassGetter<T, ?> getter, Long value) {
        return this.add(KeyMatcher.lt(Getters.field(getter), value));
    }

    /**
     * 小于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition2 lt(ClassGetter<T, ?> getter, Integer value) {
        return this.add(KeyMatcher.lt(Getters.field(getter), value));
    }

    /**
     * 小于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition2 elt(ClassGetter<T, ?> getter, Long value) {
        return this.add(KeyMatcher.elt(Getters.field(getter), value));
    }

    /**
     * 小于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public <T> QueryCondition2 elt(ClassGetter<T, ?> getter, Integer value) {
        return this.add(KeyMatcher.elt(Getters.field(getter), value));
    }

    public <T> QueryCondition2 like(ClassGetter<T, ?> getter, String value) {
        return this.add(KeyMatcher.like(Getters.field(getter), value));
    }

    public <T> QueryCondition2 likeLeft(ClassGetter<T, ?> getter, String value) {
        return this.add(KeyMatcher.likeLeft(Getters.field(getter), value));
    }

    public <T> QueryCondition2 likeRight(ClassGetter<T, ?> getter, String value) {
        return this.add(KeyMatcher.likeRight(Getters.field(getter), value));
    }

    public <T> QueryCondition2 ins(ClassGetter<T, ?> getter, Collection<String> value) {
        return this.add(KeyMatcher.strings(Getters.field(getter), value));
    }

    public <T> QueryCondition2 inl(ClassGetter<T, ?> getter, Collection<Long> value) {
        return this.add(KeyMatcher.longs(Getters.field(getter), value));
    }

    public <T> QueryCondition2 ini(ClassGetter<T, ?> getter, Collection<Integer> value) {
        return this.add(KeyMatcher.integers(Getters.field(getter), value));
    }

    public <T> QueryCondition2 desc(ClassGetter<T, ?> getter) {
        this.order = QueryOrder.descOf(Getters.field(getter));
        return this;
    }

    public <T> QueryCondition2 asc(ClassGetter<T, ?> getter) {
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
