package com.bytehonor.sdk.framework.lang.match;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @Test
    public void test3() {

        TextMatcher matcher = TextMatcher.builder().exclude("拒绝").exclude("双否", "双拒").include("满足").build();
        LOG.info("test3 matcher:{}", matcher);

        List<Boolean> list = new ArrayList<Boolean>();
        list.add(matcher.match("拒绝双否双拒满足") == false);
        list.add(matcher.match("拒绝双否双拒满足") == false);
        list.add(matcher.match("只有一个中文满足"));
        list.add(matcher.match("有拒绝和满足") == false);

        boolean isOk = true;
        for (Boolean res : list) {
            isOk = isOk && res;
            LOG.info("test3 {}", res);
        }
        assertTrue("test3", isOk);
    }

    @Test
    public void test4() {
        String text = "这是中文english夹杂 在一起的text";
        String prepare = TextMatcher.prepare(text);
        LOG.info("test4 prepare:({})", prepare);

        assertTrue("test4", "这是中文 english 夹杂 在一起的 text".equals(prepare));
    }

    @Test
    public void test5() {
        String text = "这是中文english夹杂 在一起的text";
        Set<String> words = TextMatcher.words(text);
        LOG.info("test5 words:({})", words);
        assertTrue("test5", words.contains("english") && words.contains("中文"));
    }

    @Test
    public void test6() {
        String text = "这是中文english还有123456789夹杂 在一起的text";
        String prepare = TextMatcher.prepare(text);
        LOG.info("test6 prepare:({})", prepare);

        assertTrue("test6", "这是中文 english 还有 123456789 夹杂 在一起的 text".equals(prepare));
    }

    @Test
    public void test7() {
        String text = "这是中文English还有123456789夹杂 在一起的tEXt";
        Set<String> words = TextMatcher.words(text);
        LOG.info("test7 words:({})", words);
        assertTrue("test7", words.contains("english") && words.contains("123456789"));
    }

    @Test
    public void test8() {
        String text = "🚨 🚨 🚨 🚨 🚨 🚨 🚨 🚨 🚨 🚨4,500,000,00 #USDT transferred from Tether Treasury to #Bitfinex";
        String prepare = TextMatcher.prepare(text);
        LOG.info("test8 prepare:({})", prepare);

        assertTrue("test8", "4 500 000 00 USDT transferred from Tether Treasury to Bitfinex".equals(prepare));
    }

    @Test
    public void test9() {
        TextMatcher matcher = TextMatcher.builder().include("web3").build();
        LOG.info("test9 matcher:{}", matcher);

        List<Boolean> list = new ArrayList<Boolean>();
        list.add(matcher.match("one two three four web3"));
        list.add(matcher.match("one     three four web2") == false);
        list.add(matcher.match("    two three four Web3"));
        list.add(matcher.match("        three four five") == false);
        list.add(matcher.match("        thre  four weB3"));
        list.add(matcher.match("中文WEB3的新闻"));

        boolean isOk = true;
        for (Boolean res : list) {
            isOk = isOk && res;
            LOG.info("test9 {}", res);
        }
        assertTrue("test9", isOk);
    }
}
