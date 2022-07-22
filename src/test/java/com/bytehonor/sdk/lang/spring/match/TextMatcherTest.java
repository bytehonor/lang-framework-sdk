package com.bytehonor.sdk.lang.spring.match;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextMatcherTest {

    private static final Logger LOG = LoggerFactory.getLogger(TextMatcherTest.class);

    @Test
    public void test1() {
        TextMatcher matcher = TextMatcher.builder().exclude("one").exclude("two").include("three")
                .include("four", "five").build();
        LOG.info("test1 matcher:{}", matcher);

        List<Boolean> list = new ArrayList<Boolean>();
        list.add(matcher.match("one two three four five") == false);
        list.add(matcher.match("one     three four five") == false);
        list.add(matcher.match("    two three four five") == false);
        list.add(matcher.match("        three four five"));
        list.add(matcher.match("        thre  four five"));
        list.add(matcher.match("        thre  fou  five") == false);
        list.add(matcher.match("        three fou  five"));
        list.add(matcher.match("        three fou  fiv"));

        boolean isOk = true;
        for (Boolean res : list) {
            isOk = isOk && res;
            LOG.info("test1 {}", res);
        }
        assertTrue("test1", isOk);
    }

    @Test
    public void test2() {
        TextMatcher matcher = TextMatcher.builder().exclude("one").exclude("two").exclude("three", "four")
                .include("five").build();
        LOG.info("test2 matcher:{}", matcher);

        List<Boolean> list = new ArrayList<Boolean>();
        list.add(matcher.match("one two three four five") == false);
        list.add(matcher.match("one     three four five") == false);
        list.add(matcher.match("    two three four five") == false);
        list.add(matcher.match("        three four five") == false);
        list.add(matcher.match("        thre  four five"));
        list.add(matcher.match("        thre  fou  five"));
        list.add(matcher.match("        three fou  five"));
        list.add(matcher.match("        three fou  fiv") == false);
        list.add(matcher.match("one tw  thre  four five") == false);

        boolean isOk = true;
        for (Boolean res : list) {
            isOk = isOk && res;
            LOG.info("test2 {}", res);
        }
        assertTrue("test2", isOk);
    }
}
