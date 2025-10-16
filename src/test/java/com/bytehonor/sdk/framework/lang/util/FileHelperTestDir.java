package com.bytehonor.sdk.framework.lang.util;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileHelperTestDir {

    private static Logger LOG = LoggerFactory.getLogger(FileHelperTestDir.class);

    @Test
    public void testRead() {
        String dir = "D:/test";
        List<String> list = FileKit.readDir(dir, "zip");
        for (String path : list) {
            LOG.info("paht:{}", path);
        }
    }

    // @Test
    public void testIsExistDir() {
        FileKit.pathExistOrMake("\\testfile\\2018\\");

        assertTrue("*testIsExistDir*", true);
    }

    @Test
    public void testGetFileSubfixNoDot() {
        String url = "http://f.video.weibocdn.com/004mshS7lx07z4oHvPUk01041200FWUO0E010.mp4?label=mp4_720p&template=1280x720.25.0&trans_finger=1f0da16358befad33323e3a1b7f95fc9&Expires=1576037951&ssig=Tut2Nr0831&KID=unistore,video";
        String subfix = PathKit.subfixNoDot(url);
        LOG.info("subfixNoDot:{}", subfix);
        assertTrue("*testGetFileSubfixWithDot*", "mp4".equals(subfix));
    }

    @Test
    public void testGetFileSubfixWithDot() {
        String url = "http://f.video.weibocdn.com/004mshS7lx07z4oHvPUk01041200FWUO0E010.mp4?label=mp4_720p&template=1280x720.25.0&trans_finger=1f0da16358befad33323e3a1b7f95fc9&Expires=1576037951&ssig=Tut2Nr0831&KID=unistore,video";
        String subfix = PathKit.subfixWithDot(url);
        LOG.info("subfixWithDot:{}", subfix);
        assertTrue("*testGetFileSubfixWithDot*", ".mp4".equals(subfix));
    }

}
