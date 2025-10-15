package com.bytehonor.sdk.framework.lang.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MapSorter {

    /**
     * 使用 Map按value进行排序，生序
     * 
     * @param map
     * @return
     */
    public static Map<String, Integer> ascStringInteger(Map<String, Integer> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(oriMap.entrySet());
        Collections.sort(entryList, new StringIntegerAscComparator());

        Iterator<Map.Entry<String, Integer>> iter = entryList.iterator();
        Map.Entry<String, Integer> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }

    /**
     * 使用 Map按value进行排序，降序
     * 
     * @param map
     * @return
     */
    public static Map<String, Integer> descStringInteger(Map<String, Integer> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(oriMap.entrySet());
        Collections.sort(entryList, new StringIntegerDescComparator());

        Iterator<Map.Entry<String, Integer>> iter = entryList.iterator();
        Map.Entry<String, Integer> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }

    public static Map<String, Long> descStringLong(Map<String, Long> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, Long> sortedMap = new LinkedHashMap<String, Long>();
        List<Map.Entry<String, Long>> entryList = new ArrayList<Map.Entry<String, Long>>(oriMap.entrySet());
        Collections.sort(entryList, new StringLongDescComparator());

        Iterator<Map.Entry<String, Long>> iter = entryList.iterator();
        Map.Entry<String, Long> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }

    /**
     * 使用 Map按value进行排序，降序
     * 
     * @param map
     * @return
     */
    public static Map<Long, Integer> descLongInteger(Map<Long, Integer> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<Long, Integer> sortedMap = new LinkedHashMap<Long, Integer>();
        List<Map.Entry<Long, Integer>> entryList = new ArrayList<Map.Entry<Long, Integer>>(oriMap.entrySet());
        Collections.sort(entryList, new LongIntegerDescComparator());

        Iterator<Map.Entry<Long, Integer>> iter = entryList.iterator();
        Map.Entry<Long, Integer> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }

    static class StringIntegerAscComparator implements Comparator<Map.Entry<String, Integer>> {

        @Override
        public int compare(Entry<String, Integer> me1, Entry<String, Integer> me2) {
            return me1.getValue().compareTo(me2.getValue());
        }

    }

    static class StringIntegerDescComparator implements Comparator<Map.Entry<String, Integer>> {

        @Override
        public int compare(Entry<String, Integer> me1, Entry<String, Integer> me2) {
            return me2.getValue().compareTo(me1.getValue());
        }

    }

    static class StringLongDescComparator implements Comparator<Map.Entry<String, Long>> {

        @Override
        public int compare(Entry<String, Long> me1, Entry<String, Long> me2) {
            return me2.getValue().compareTo(me1.getValue());
        }

    }

    static class LongIntegerAscComparator implements Comparator<Map.Entry<Long, Integer>> {

        @Override
        public int compare(Entry<Long, Integer> me1, Entry<Long, Integer> me2) {
            return me1.getValue().compareTo(me2.getValue());
        }

    }

    static class LongIntegerDescComparator implements Comparator<Map.Entry<Long, Integer>> {

        @Override
        public int compare(Entry<Long, Integer> me1, Entry<Long, Integer> me2) {
            return me2.getValue().compareTo(me1.getValue());
        }

    }

}
