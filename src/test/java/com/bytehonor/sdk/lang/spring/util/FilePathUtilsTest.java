package com.bytehonor.sdk.lang.spring.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FilePathUtilsTest {

    @Test
    public void testMergeDir() {
        String rootDir = "/dir/";
        String sub1 = "name";
        String sub2 = "name";
        String sub3 = "/name";
        String sub4 = "/name/";
        String target = "/dir/name/";
        boolean res1 = target.equals(FilePathUtils.connectDirWithEnd(rootDir, sub1));
        boolean res2 = target.equals(FilePathUtils.connectDirWithEnd(rootDir, sub2));
        boolean res3 = target.equals(FilePathUtils.connectDirWithEnd(rootDir, sub3));
        boolean res4 = target.equals(FilePathUtils.connectDirWithEnd(rootDir, sub4));
        boolean res = res1 && res2 && res3 && res4;
        assertTrue("*testMergeDir*", res);
    }

}
