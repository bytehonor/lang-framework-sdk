package com.bytehonor.sdk.lang.bytehonor.util;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileReadWriteUtilsTest {

    private static Logger LOG = LoggerFactory.getLogger(FileReadWriteUtilsTest.class);

    @Test
    public void testRead() {
        String dir = "D:/test";
        List<String> list = FileReadWriteUtils.readDir(dir, "zip");
        for (String path : list) {
            LOG.info("paht:{}", path);
        }
    }

}
