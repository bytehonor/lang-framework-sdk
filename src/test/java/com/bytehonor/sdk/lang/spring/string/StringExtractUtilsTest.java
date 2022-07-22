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
        String header = "<a src=";
        String footer = "/>";
        String fullText = footer + "asdfasdfasdfasdfasdf[][][][][][*(77asdf" + header + text + footer
                + "asdfasdfasd/>fas/>dfasdf";
        String res = StringExtractUtils.extract(fullText, header, footer);
        LOG.info("testExtract:{}", res);
        assertTrue("*testExtract*", res.equals(header + text + footer));
    }

    @Test
    public void testExtract2() {
        String text = "昨日全天上海无新增新型冠状病毒肺炎确诊病例";
        String header = "<";
        String footer = ">";
        String fullText = footer + "asdfasdfasdfasdfasdf[][][][][][*(77asdf" + header + text + footer
                + "asdfasdfasd/>fas/>dfasdf";
        String res = StringExtractUtils.extract(fullText, header, footer);
        LOG.info("testExtract2:{}", res);
        assertTrue("*testExtract2*", res.equals(header + text + footer));
    }

    @Test
    public void testExtract3() {
        String text = "昨日全天上海无新增新型冠状病毒肺炎确诊病例";
        String header1 = "<* *rc="; // 模糊识别
        String header2 = "<x xrc=";
        String footer = "/>";
        String fullText = footer + "asdfasdfasdfasdfasdf[][][][][][*(77asdf" + header2 + text + footer
                + "asdfasdfasd/>fas/>dfasdf";
        String res = StringExtractUtils.extract(fullText, header1, footer); // 模糊识别
        LOG.info("testExtract3:{}", res);
        assertTrue("*testExtract3*", res.equals(header2 + text + footer));
    }

    @Test
    public void testExtractTrim() {
        String text = "昨日全天上海无新增新型冠状病毒肺炎确诊病例";
        String header = "<a src=";
        String footer = "/>";
        String fullText = footer + "asdfasdfasdfasdfasdf[][][][][][*(77asdf" + header + text + footer
                + "asdfasdfasd/>fas/>dfasdf";
        String res = StringExtractUtils.extractTrim(fullText, header, footer);
        LOG.info("testExtractTrim:{}", res);
        assertTrue("*testExtractTrim*", res.equals(text));
    }

    @Test
    public void testExtractTrim2() {
        String text = "昨日全天上海无新增新型冠状病毒肺炎确诊病例";
        String header = "<";
        String footer = ">";
        String fullText = footer + "asdfasdfasdfasdfasdf[][][][][][*(77asdf" + header + text + footer
                + "asdfasdfasd/>fas/>dfasdf";
        String res = StringExtractUtils.extractTrim(fullText, header, footer);
        LOG.info("testExtractTrim2:{}", res);
        assertTrue("*testExtractTrim2*", res.equals(text));
    }

    @Test
    public void testExtractTrim3() {
        String text = "昨日全天上海无新增新型冠状病毒肺炎确诊病例";
        String header1 = "<* *rc="; // 模糊识别
        String header2 = "<x xrc=";
        String footer = "/>";
        String fullText = footer + "asdfasdfasdfasdfasdf[][][][][][*(77asdf" + header2 + text + footer
                + "asdfasdfasd/>fas/>dfasdf";
        String res = StringExtractUtils.extractTrim(fullText, header1, footer); // 模糊识别
        LOG.info("testExtractTrim3:{}", res);
        assertTrue("*testExtractTrim3*", res.equals(text));
    }
}
