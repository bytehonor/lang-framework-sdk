package com.bytehonor.sdk.lang.spring.match;

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

        boolean isOk1 = matcher.isHit(Sets.newHashSet("one", "two"));
        boolean isOk2 = matcher.isHit(Sets.newHashSet("one")) == false;
        boolean isOk3 = matcher.isHit(Sets.newHashSet("two")) == false;
        boolean isOk4 = matcher.isHit(Sets.newHashSet("one", "two", "three"));
        boolean isOk5 = matcher.isHit(Sets.newHashSet("one1", "two", "three")) == false;

        LOG.info("test1 {}, {}, {}, {}, {}", isOk1, isOk2, isOk3, isOk4, isOk5);
        assertTrue("test1", isOk1 && isOk2 && isOk3 && isOk4 && isOk5);
    }

    @Test
    public void test2() {
        boolean isOk = false;
        try {
            WordMatcher.of("", "asdfasf");
        } catch (Exception e) {
            isOk = true;
            LOG.error("test2", e);
        }
        assertTrue("test2", isOk);
    }
}
