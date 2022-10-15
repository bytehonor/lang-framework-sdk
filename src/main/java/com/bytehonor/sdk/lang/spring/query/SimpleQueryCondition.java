package com.bytehonor.sdk.lang.spring.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import com.bytehonor.sdk.lang.spring.constant.HttpConstants;
import com.bytehonor.sdk.lang.spring.constant.QueryLogic;
import com.bytehonor.sdk.lang.spring.match.KeyMatcher;

/**
 * 
 * @author lijianqiang
 *
 */
public final class SimpleQueryCondition {

    private static final int LIMIT_DEF = HttpConstants.LIMIT_DEF;

    private static final int LIMIT_ALL = HttpConstants.LIMIT_NON;

    private final QueryLogic logic;

    private final QueryPage page;

    private QueryOrder order;

    private final List<KeyMatcher> matchers;

    private SimpleQueryCondition(QueryLogic logic, QueryPage page) {
        this.logic = logic;
        this.page = page;
        this.order = null;
        this.matchers = new ArrayList<KeyMatcher>();
    }

    public static SimpleQueryCondition one() {
        return and(0, 1);
    }

    public static SimpleQueryCondition and() {
        return and(0, LIMIT_DEF);
    }

    public static SimpleQueryCondition all() {
        return and(0, LIMIT_ALL);
    }

    public static SimpleQueryCondition and(int offset, int limit) {
        return create(QueryLogic.AND, QueryPage.of(offset, limit));
    }

    public static SimpleQueryCondition or() {
        return or(0, LIMIT_DEF);
    }

    public static SimpleQueryCondition or(int offset, int limit) {
        return create(QueryLogic.OR, QueryPage.of(offset, limit));
    }

    public static SimpleQueryCondition create(QueryLogic logic, QueryPage page) {
        Objects.requireNonNull(logic, "logic");
        Objects.requireNonNull(page, "page");

        return new SimpleQueryCondition(logic, page);
    }

    private SimpleQueryCondition safeAdd(KeyMatcher matcher) {
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
    public SimpleQueryCondition eq(String key, String value) {
        return this.safeAdd(KeyMatcher.eq(key, value));
    }

    /**
     * 等于
     * 
     * @param key
     * @param value
     * @return
     */
    public SimpleQueryCondition eq(String key, Long value) {
        return this.safeAdd(KeyMatcher.eq(key, value));
    }

    /**
     * 等于
     * 
     * @param key
     * @param value
     * @return
     */
    public SimpleQueryCondition eq(String key, Integer value) {
        return this.safeAdd(KeyMatcher.eq(key, value));
    }

    /**
     * 等于
     * 
     * @param key
     * @param value
     * @return
     */
    public SimpleQueryCondition eq(String key, Boolean value) {
        return this.safeAdd(KeyMatcher.eq(key, value));
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public SimpleQueryCondition neq(String key, String value) {
        return this.safeAdd(KeyMatcher.neq(key, value));
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public SimpleQueryCondition neq(String key, Long value) {
        return this.safeAdd(KeyMatcher.neq(key, value));
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public SimpleQueryCondition neq(String key, Integer value) {
        return this.safeAdd(KeyMatcher.neq(key, value));
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public SimpleQueryCondition neq(String key, Boolean value) {
        return this.safeAdd(KeyMatcher.neq(key, value));
    }

    /**
     * 大于
     * 
     * @param key
     * @param value
     * @return
     */
    public SimpleQueryCondition gt(String key, Long value) {
        return this.safeAdd(KeyMatcher.gt(key, value));
    }

    /**
     * 大于
     * 
     * @param key
     * @param value
     * @return
     */
    public SimpleQueryCondition gt(String key, Integer value) {
        return this.safeAdd(KeyMatcher.gt(key, value));
    }

    /**
     * 大于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public SimpleQueryCondition egt(String key, Long value) {
        return this.safeAdd(KeyMatcher.egt(key, value));
    }

    /**
     * 大于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public SimpleQueryCondition egt(String key, Integer value) {
        return this.safeAdd(KeyMatcher.egt(key, value));
    }

    /**
     * 小于
     * 
     * @param key
     * @param value
     * @return
     */
    public SimpleQueryCondition lt(String key, Long value) {
        return this.safeAdd(KeyMatcher.lt(key, value));
    }

    /**
     * 小于
     * 
     * @param key
     * @param value
     * @return
     */
    public SimpleQueryCondition lt(String key, Integer value) {
        return this.safeAdd(KeyMatcher.lt(key, value));
    }

    /**
     * 小于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public SimpleQueryCondition elt(String key, Long value) {
        return this.safeAdd(KeyMatcher.elt(key, value));
    }

    /**
     * 小于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public SimpleQueryCondition elt(String key, Integer value) {
        return this.safeAdd(KeyMatcher.elt(key, value));
    }

    public SimpleQueryCondition like(String key, String value) {
        return this.safeAdd(KeyMatcher.like(key, value));
    }

    public SimpleQueryCondition likeLeft(String key, String value) {
        return this.safeAdd(KeyMatcher.likeLeft(key, value));
    }

    public SimpleQueryCondition likeRight(String key, String value) {
        return this.safeAdd(KeyMatcher.likeRight(key, value));
    }

    public SimpleQueryCondition strings(String key, Collection<String> value) {
        return this.safeAdd(KeyMatcher.strings(key, value));
    }

    public SimpleQueryCondition longs(String key, Collection<Long> value) {
        return this.safeAdd(KeyMatcher.longs(key, value));
    }

    public SimpleQueryCondition integers(String key, Collection<Integer> value) {
        return this.safeAdd(KeyMatcher.integers(key, value));
    }

    public SimpleQueryCondition descBy(String key) {
        this.order = QueryOrder.descOf(key);
        return this;
    }

    public SimpleQueryCondition ascBy(String key) {
        this.order = QueryOrder.ascOf(key);
        return this;
    }

    public void setOffset(int offset) {
        this.page.setOffset(offset);
    }

    public void setLimit(int limit) {
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
