package com.bytehonor.sdk.lang.bytehonor.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5UtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(MD5UtilsTest.class);
    
	@Test
	public void testMd5() {
		long now = System.currentTimeMillis();
		
		String md5 = MD5Utils.md5("1910259602" + "8b98f937554a180c9a6a" + now);
		LOG.info("md5:{}", md5);
		System.out.println("now:" + now);
		System.out.println("md5:" + md5.toLowerCase());
		boolean isOk = md5 != null && md5.length() > 30;
		assertTrue("testBuild", isOk);
	}

}
