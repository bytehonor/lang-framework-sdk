package com.bytehonor.sdk.lang.bytehonor.string;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringSliceUtilsTest {
    
    private static final Logger LOG = LoggerFactory.getLogger(StringSliceUtilsTest.class);

    @Test
    public void testSlice() {
        String text = "昨日全天上海无新增新型冠状病毒肺炎确诊病例";
        Set<String> set = StringSliceUtils.slice(text);
        for (String s : set) {
            LOG.info("slice:{}", s);
        }
        assertTrue("*testSlice*", true);
    }

    @Test
    public void testSlice3() {
        String text = "昨日全天上海无新增新型冠状病毒肺炎确诊病例";
        Set<String> set = StringSliceUtils.slice3(text);
        for (String s : set) {
            LOG.info("slice3:{}", s);
        }
        assertTrue("*testSlice3*", true);
    }

    @Test
    public void testSliceStringInt() {
        String text = "昨日全天上海无新增新型冠状病毒肺炎确诊病例";
        Set<String> set = StringSliceUtils.slice(text, 4);
        for (String s : set) {
            LOG.info("slice4:{}", s);
        }
        assertTrue("*testSliceStringInt*", true);
    }

    @Test
    public void testSliceOrderd() {
        String text = "昨日全天上海无新增新型冠状病毒肺炎确诊病例";
        List<String> list = StringSliceUtils.sliceOrderd(text, 3);
        for (String s : list) {
            LOG.info("order:{}", s);
        }
        assertTrue("*testSlice*", true);
    }

}
