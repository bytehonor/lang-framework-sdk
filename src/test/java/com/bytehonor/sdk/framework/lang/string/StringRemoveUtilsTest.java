package com.bytehonor.sdk.framework.lang.string;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringRemoveUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(StringRemoveUtilsTest.class);

    private static final String SPECIAL1 = "2018\r\n\t\bb egin°′″＄￥〒￠￡％＠℃℉﹩﹪‰﹫㎡㏕㎜㎝㎞㏎m³㎎㎏㏄º○¤%$º¹²³end";

    private static final String SPECIAL2 = "2018\r\n\t\bb egin€£Ұ₴$₰¢₤¥₳₲₪₵元₣₱฿¤₡₮₭₩ރ円₢₥₫₦zł﷼₠₧₯₨Kčर₹ƒ₸￠end";

    @Test
    public void testRemove() {
        String src = "0123456789";
        int length = src.length();
        LOG.info("testRemove 1:{}, length:{}", src, length);

        boolean isOk1 = false;
        try {
            LOG.info("testRemove 1:{}", StringRemoveUtils.remove(src, -1, 5));
        } catch (Exception e) {
            isOk1 = true;
            LOG.info("isOk1:{}, {}", isOk1, e.getMessage());
        }
        boolean isOk2 = false;
        try {
            LOG.info("testRemove 1:{}", StringRemoveUtils.remove(src, 0, 15));
        } catch (Exception e) {
            isOk2 = true;
            LOG.info("isOk2:{}, {}", isOk2, e.getMessage());
        }
        boolean isOk3 = false;
        try {
            LOG.info("testRemove 1:{}", StringRemoveUtils.remove(src, 9, 3));
        } catch (Exception e) {
            isOk3 = true;
            LOG.info("isOk3:{}, {}", isOk3, e.getMessage());
        }

        String remove = StringRemoveUtils.remove(src, 1, 1);
        boolean isOk4 = remove.equals("023456789");
        LOG.info("isOk4:{}, remove:{}", isOk4, remove);

        remove = StringRemoveUtils.remove(src, 1, 5);
        boolean isOk5 = remove.equals("06789");
        LOG.info("isOk5:{}, remove:{}", isOk5, remove);

        remove = StringRemoveUtils.remove(src, 2, 5);
        boolean isOk6 = remove.equals("016789");
        LOG.info("isOk6:{}, remove:{}", isOk6, remove);

        remove = StringRemoveUtils.remove(src, 0, length);
        boolean isOk7 = remove.equals("");
        LOG.info("isOk7:{}, remove:{}", isOk7, remove);

        remove = StringRemoveUtils.remove(src, 1, length);
        boolean isOk8 = remove.equals("0");
        LOG.info("isOk8:{}, remove:{}", isOk8, remove);

        boolean isOk = isOk1 && isOk2 && isOk3 && isOk4 && isOk5 && isOk6 && isOk7 && isOk8;
        assertTrue("*testRemove*", isOk);
    }

    @Test
    public void testReplaceNonChineseWithBlank() {
        LOG.info("replaceNonChineseWithBlank SPECIAL1:({})", StringRemoveUtils.replaceNonChineseWithBlank(SPECIAL1));
        LOG.info("replaceNonChineseWithBlank SPECIAL2:({})", StringRemoveUtils.replaceNonChineseWithBlank(SPECIAL2));
        assertTrue("*testReplaceNonChineseWithBlank*", true);
    }

    @Test
    public void testRemoveNonChinese() {
        LOG.info("removeNonChinese SPECIAL1:({})", StringRemoveUtils.removeNonChinese(SPECIAL1));
        LOG.info("removeNonChinese SPECIAL2:({})", StringRemoveUtils.removeNonChinese(SPECIAL2));
        assertTrue("*testRemoveNonChinese*", true);
    }

    @Test
    public void testReplaceNonNormalWithBlank() {
        LOG.info("replaceNonNormalWithBlank SPECIAL1:({})", StringRemoveUtils.replaceNonNormalWithBlank(SPECIAL1));
        LOG.info("replaceNonNormalWithBlank SPECIAL2:({})", StringRemoveUtils.replaceNonNormalWithBlank(SPECIAL2));
        assertTrue("*testReplaceNonChineseWithBlank*", true);
    }

    @Test
    public void testRemoveNonNormal() {
        LOG.info("removeNonNormal SPECIAL1:({})", StringRemoveUtils.removeNonNormal(SPECIAL1));
        LOG.info("removeNonNormal SPECIAL2:({})", StringRemoveUtils.removeNonNormal(SPECIAL2));
        assertTrue("*testRemoveNonChinese*", true);
    }

}
