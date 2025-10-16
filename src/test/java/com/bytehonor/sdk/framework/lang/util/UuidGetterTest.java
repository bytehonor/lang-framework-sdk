package com.bytehonor.sdk.framework.lang.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UuidGetterTest {

	@Test
	public void testGetSimple() {
		String src = UuidGetter.getSimple();
		boolean isOk = src != null && src.length() == 32;
		assertTrue("testGetSimple", isOk);
	}

	@Test
	public void testGetFull() {
		String src = UuidGetter.getFull();
		boolean isOk = src != null && src.length() == 36;
		assertTrue("testGetFull", isOk);
	}

	public void testTime() {
		int size = 10000;
		long start1 = System.nanoTime();
		for (int i=0;i<size;i++) {
			UuidGetter.getFull().replaceAll("-", "");
		}
		long cost1 = System.nanoTime() - start1;
		
		long start2 = System.nanoTime();
		for (int i=0;i<size;i++) {
			UuidGetter.getSimple();
		}
		long cost2 = System.nanoTime() - start2;
		
		System.out.println("cost1:" + cost1 + ", cost2:" + cost2 + ", diff:" + (cost1 - cost2));
	}
}
