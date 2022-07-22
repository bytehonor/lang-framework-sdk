package com.bytehonor.sdk.lang.spring.string;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringExtractUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(StringExtractUtilsTest.class);

    @Test
    public void testExtract() {
        String text = "昨日全天上海无新增新型冠状病毒肺炎确诊病例";
        String beginner = "<a src=";
        String ender = "/>";
        String fullText = ender + "asdfasdfasdfasdfasdf[][][][][][*(77asdf" + beginner + text + ender
                + "asdfasdfasd/>fas/>dfasdf";
        String res = StringExtractUtils.extract(fullText, beginner, ender);
        LOG.info("testExtract:{}", res);
        assertTrue("*testExtract*", res.equals(beginner + text + ender));
    }

    @Test
    public void testExtract2() {
        String text = "昨日全天上海无新增新型冠状病毒肺炎确诊病例";
        String beginner = "<";
        String ender = ">";
        String fullText = ender + "asdfasdfasdfasdfasdf[][][][][][*(77asdf" + beginner + text + ender
                + "asdfasdfasd/>fas/>dfasdf";
        String res = StringExtractUtils.extract(fullText, beginner, ender);
        LOG.info("testExtract2:{}", res);
        assertTrue("*testExtract2*", res.equals(beginner + text + ender));
    }

    @Test
    public void testExtractTrim() {
        String text = "昨日全天上海无新增新型冠状病毒肺炎确诊病例";
        String beginner = "<a src=";
        String ender = "/>";
        String fullText = ender + "asdfasdfasdfasdfasdf[][][][][][*(77asdf" + beginner + text + ender
                + "asdfasdfasd/>fas/>dfasdf";
        String res = StringExtractUtils.extractTrim(fullText, beginner, ender);
        LOG.info("testExtractTrim:{}", res);
        assertTrue("*testExtractTrim*", res.equals(text));
    }

    @Test
    public void testExtractTrim2() {
        String text = "昨日全天上海无新增新型冠状病毒肺炎确诊病例";
        String beginner = "<";
        String ender = ">";
        String fullText = ender + "asdfasdfasdfasdfasdf[][][][][][*(77asdf" + beginner + text + ender
                + "asdfasdfasd/>fas/>dfasdf";
        String res = StringExtractUtils.extractTrim(fullText, beginner, ender);
        LOG.info("testExtractTrim2:{}", res);
        assertTrue("*testExtractTrim2*", res.equals(text));
    }
}
