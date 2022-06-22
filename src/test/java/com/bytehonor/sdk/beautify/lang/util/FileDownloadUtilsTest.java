package com.bytehonor.sdk.beautify.lang.util;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileDownloadUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(FileDownloadUtilsTest.class);

    @Test
    public void testDownload() {
        String url = "https://huajietaojin.oss-cn-hangzhou.aliyuncs.com/columbus/91d27a04666b49d4be9da5562ae6059a/store/logo/5a5dd285046116257b1d8c97d208b70e.jpg";
        File file = null;
        try {
            file = FileDownloadUtils.download(url, "D:/test/11/22", "mylogo.jpg");
            LOG.info("fileName:{}, length:{}", file.getName(), file.length());
        } catch (Exception e) {
            LOG.error("error", e);
        }
        assertTrue("*testDownload*", file != null);
    }
}
