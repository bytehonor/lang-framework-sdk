package com.bytehonor.sdk.framework.lang.string;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.constant.CharConstants;

public class StringSplitUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(StringSplitUtilsTest.class);

    private static final String TEXT = " 1 a bc def       adsfasdfasdf ";

    @Test
    public void test() {
        String src = "aaa,bbb ，c cc,ddd，d，，   ，，,,,x";
        List<String> words = StringSplitUtils.split(src);
        for (String s : words) {
            LOG.info("0:({})", s);
        }

        assertTrue("*test*", true);
    }

    @Test
    public void testSplitString() {
        List<String> list = StringSplitUtils.splitWithBlank(TEXT);
        for (String s : list) {
            LOG.info("1.({})", s);
        }
        String[] arr = TEXT.split(" ");
        for (String s : arr) {
            LOG.info("2.({})", s);
        }

        int total = 1000000;
        long start1 = System.nanoTime();
        for (int i = 0; i < total; i++) {
            StringSplitUtils.split(TEXT, CharConstants.BLANK);
        }
        long cost1 = System.nanoTime() - start1;

        long start2 = System.nanoTime();
        for (int i = 0; i < total; i++) {
            TEXT.split(" ");
        }
        long cost2 = System.nanoTime() - start2;

        LOG.info("cost1:{}, cost2:{}, diff:{}", cost1, cost2, (cost1 - cost2));
        assertTrue("*testSplitString*", true);
    }

    @Test
    public void test3() {
        String src = "aaaaa";
        List<String> words = StringSplitUtils.split(src);
        for (String s : words) {
            LOG.info("test3:({})", s);
        }

        assertTrue("*test*", true);
    }

}
