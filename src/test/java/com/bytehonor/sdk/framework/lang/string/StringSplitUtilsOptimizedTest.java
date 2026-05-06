package com.bytehonor.sdk.framework.lang.string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.bytehonor.sdk.framework.lang.constant.CharConstants;

public class StringSplitUtilsOptimizedTest {

    @Test
    public void split_shouldNormalizeChineseCommaAndSkipEmptyParts() {
        String src = "aaa,bbb ，c cc,ddd，d，，   ，，,,,x";
        List<String> words = StringSplitUtils.split(src);
        assertEquals(Arrays.asList("aaa", "bbb ", "c cc", "ddd", "d", "   ", "x"), words);
    }

    @Test
    public void splitWithBlank_shouldSkipRepeatedAndBoundaryBlanks() {
        String text = " 1 a bc def       adsfasdfasdf ";
        List<String> words = StringSplitUtils.splitWithBlank(text);
        assertEquals(Arrays.asList("1", "a", "bc", "def", "adsfasdfasdf"), words);
    }

    @Test
    public void split_shouldReturnSinglePartWhenNoDelimiter() {
        assertEquals(Arrays.asList("aaaaa"), StringSplitUtils.split("aaaaa"));
    }

    @Test
    public void split_shouldReturnEmptyForNullOrBlankInput() {
        assertTrue(StringSplitUtils.split((String) null).isEmpty());
        assertTrue(StringSplitUtils.split("").isEmpty());
    }

    @Test
    public void toSet_shouldUseSameDelimiterRulesAsSplit() {
        Set<String> words = StringSplitUtils.toSet("a  b a ", CharConstants.BLANK);
        assertEquals(new HashSet<>(Arrays.asList("a", "b")), words);
    }
}
