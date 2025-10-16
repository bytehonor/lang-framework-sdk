package com.bytehonor.sdk.framework.lang.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PathKitTest {

    private static Logger LOG = LoggerFactory.getLogger(PathKitTest.class);

    @Test
    public void testGetSubfixNoDot() {
        String url = "http://f.video.weibocdn.com/004mshS7lx07z4oHvPUk01041200FWUO0E010.mp4?label=mp4_720p&template=1280x720.25.0&trans_finger=1f0da16358befad33323e3a1b7f95fc9&Expires=1576037951&ssig=Tut2Nr0831&KID=unistore,video";
        String subfix = PathKit.subfixNoDot(url);
        LOG.info("subfixNoDot:{}", subfix);
        assertTrue("*testGetSubfixNoDot*", "mp4".equals(subfix));
    }

    @Test
    public void testGetSubfixWithDot() {
        String url = "http://f.video.weibocdn.com/004mshS7lx07z4oHvPUk01041200FWUO0E010.mp4?label=mp4_720p&template=1280x720.25.0&trans_finger=1f0da16358befad33323e3a1b7f95fc9&Expires=1576037951&ssig=Tut2Nr0831&KID=unistore,video";
        String subfix = PathKit.subfixWithDot(url);
        LOG.info("subfixWithDot:{}", subfix);
        assertTrue("*testGetSubfixWithDot*", ".mp4".equals(subfix));
    }

}
