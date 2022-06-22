package com.bytehonor.sdk.beautify.lang.string;

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
        String fullText = "asdfasdfasdfasdfasdf[][][][][][*(77asdf" + beginner + text + ender
                + "asdfasdfasd/>fas/>dfasdf";
        String res = StringExtractUtils.extract(fullText, beginner, ender);
        LOG.info("order:{}", res);
        assertTrue("*testExtract*", res.equals(beginner + text + ender));
    }

}
