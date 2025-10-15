package com.bytehonor.sdk.framework.lang.nlp;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.string.StringRemoveUtils;

public class TextNlpUtilsTest {
    private static final Logger LOG = LoggerFactory.getLogger(TextNlpUtilsTest.class);

    @Test
    public void test() {
        List<String> list = new ArrayList<String>();
        list.add("https://aasdfasd.xxx 123456 https://aasdfasd.xxx 789");
        list.add("123456 https://aasdfasd.xxx https://aasdfasd.xxx https://aasdfasd.xxx 789");
        list.add("123456 ahttps://aasdfasd.xxx 789");
        list.add("123456 http://aasdfasd.xxx 789");
        list.add("【香港街头，只有他的镜头对准暴徒】http://t.cn/Ai8LbJIv .");

        for (String src : list) {
            LOG.info("remove:({})", StringRemoveUtils.removeHttp(src));
        }
    }

}
