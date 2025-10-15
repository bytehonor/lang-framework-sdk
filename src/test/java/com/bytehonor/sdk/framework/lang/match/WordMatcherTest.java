package com.bytehonor.sdk.framework.lang.match;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;

public class WordMatcherTest {

    private static final Logger LOG = LoggerFactory.getLogger(WordMatcherTest.class);

    @Test
    public void test1() {
        WordMatcher matcher = WordMatcher.of("one    ", "     two");
        LOG.info("matcher:{}", matcher);

        boolean isOk1 = matcher.match(Sets.newHashSet("one", "two"));
        boolean isOk2 = matcher.match(Sets.newHashSet("one")) == false;
        boolean isOk3 = matcher.match(Sets.newHashSet("two")) == false;
        boolean isOk4 = matcher.match(Sets.newHashSet("one", "two", "three"));
        boolean isOk5 = matcher.match(Sets.newHashSet("one1", "two", "three")) == false;

        LOG.info("test1 {}, {}, {}, {}, {}", isOk1, isOk2, isOk3, isOk4, isOk5);
        assertTrue("test1", isOk1 && isOk2 && isOk3 && isOk4 && isOk5);
    }

    @Test
    public void test2() {
        boolean isOk = true;
        try {
            WordMatcher matcher = WordMatcher.of("", "asdfasf");
            LOG.info("test2 matcher:{}", matcher);
        } catch (Exception e) {
            isOk = false;
            LOG.error("test2", e);
        }
        assertTrue("test2", isOk);
    }
}
