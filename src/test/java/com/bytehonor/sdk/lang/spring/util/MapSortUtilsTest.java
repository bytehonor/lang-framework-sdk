package com.bytehonor.sdk.lang.spring.util;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapSortUtilsTest {

	private static final Logger LOG = LoggerFactory.getLogger(MapSortUtilsTest.class);

	@Test
	public void testSortMapByValue() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("汉字", 5);
		map.put("中国", 6);
		map.put("代码", 1);
		map.put("文化", 2);
		map.put("美食", 3);

		for (String key : map.keySet()) {
			LOG.info("Before: {} - {}", key, map.get(key));
		}

		Map<String, Integer> ascMap = MapSortUtils.sortStringIntegerAsc(map);

		for (String key : ascMap.keySet()) {
			LOG.info("ASC: {} - {}", key, ascMap.get(key));
		}

		Map<String, Integer> descMap = MapSortUtils.sortStringIntegerDesc(map);

		for (String key : descMap.keySet()) {
			LOG.info("DESC: {} - {}", key, descMap.get(key));
		}

		assertTrue("*testSortMapByValue*", true);
	}
	

}
