package com.bytehonor.sdk.lang.bytehonor.util;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileDownloadUtilsTest {
    
    private static final Logger LOG = LoggerFactory.getLogger(FileDownloadUtilsTest.class);

    @Test
    public void testGetFileSubfixNoDot() {
        String url = "http://f.video.weibocdn.com/004mshS7lx07z4oHvPUk01041200FWUO0E010.mp4?label=mp4_720p&template=1280x720.25.0&trans_finger=1f0da16358befad33323e3a1b7f95fc9&Expires=1576037951&ssig=Tut2Nr0831&KID=unistore,video";
        String subfix = FileDownloadUtils.subfixNoDot(url);
        LOG.info("subfix:{}", subfix);
        assertTrue("*testGetFileSubfixWithDot*", "mp4".equals(subfix));
    }

    @Test
    public void testGetFileSubfixWithDot() {
        String url = "http://f.video.weibocdn.com/004mshS7lx07z4oHvPUk01041200FWUO0E010.mp4?label=mp4_720p&template=1280x720.25.0&trans_finger=1f0da16358befad33323e3a1b7f95fc9&Expires=1576037951&ssig=Tut2Nr0831&KID=unistore,video";
        String subfix = FileDownloadUtils.subfixWithDot(url);
        LOG.info("subfix:{}", subfix);
        assertTrue("*testGetFileSubfixWithDot*", ".mp4".equals(subfix));
    }

    @Test
    public void testByte2File() {

//        byte[] qrcodeBytes = QrcodeUtils.createQrcode("hello world 12313", null);
//        FileUtils.byte2File(qrcodeBytes, "\\testfile\\", System.currentTimeMillis() + "youlogode.jpg");

        assertTrue("*testByte2File*", true);
    }

//    @Test
    public void testDownload() {
        String url = "https://huajietaojin.oss-cn-hangzhou.aliyuncs.com/columbus/91d27a04666b49d4be9da5562ae6059a/store/logo/5a5dd285046116257b1d8c97d208b70e.jpg";
        File file = null;
        try {
            file = FileDownloadUtils.download(url, "D:\\data", "mylogo.jpg");

            // byte[] qrcodeBytes = QrcodeUtils.createQrcode("hello world 12313", file);
            // FileUtils.byte2File(qrcodeBytes, "D:\\file\\", System.currentTimeMillis() +
            // "qrcodelogo.jpg");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertTrue("*testDownload*", file != null);
    }

//    @Test
    public void testIsExistDir() {
        FileDownloadUtils.isExistDir("\\testfile\\2018\\");

        assertTrue("*testIsExistDir*", true);
    }
    
//    @Test
//    public void testMd5Rename() {
//        String url = "https://huajietaojin.oss-cn-hangzhou.aliyuncs.com/columbus/91d27a04666b49d4be9da5562ae6059a/store/logo/5a5dd285046116257b1d8c97d208b70e.jpg";
//        String name = FileUtils.md5Rename(url);
//        LOG.info("md5Rename:{}", name);
//        assertTrue("*testIsExistDir*", "6e086e9e3811573716842ce363527e74.jpg".equals(name));
//    }

}
