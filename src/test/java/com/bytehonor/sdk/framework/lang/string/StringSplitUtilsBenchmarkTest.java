package com.bytehonor.sdk.framework.lang.string;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.constant.CharConstants;

/**
 * Manual micro-benchmark for rough comparison only.
 * Skip in CI because machine noise can affect absolute numbers.
 */
@Ignore("Manual benchmark, run locally when needed")
public class StringSplitUtilsBenchmarkTest {

    private static final Logger LOG = LoggerFactory.getLogger(StringSplitUtilsBenchmarkTest.class);

    @Test
    public void benchmarkSplitWithBlank() {
        String text = " 1 a bc def       adsfasdfasdf  xyz mmmmm nnnnnn qqqqqq ";
        int warmup = 50_000;
        int rounds = 1_000_000;

        for (int i = 0; i < warmup; i++) {
            StringSplitUtils.split(text, CharConstants.BLANK);
            text.split(" ");
        }

        long start1 = System.nanoTime();
        for (int i = 0; i < rounds; i++) {
            StringSplitUtils.split(text, CharConstants.BLANK);
        }
        long cost1 = System.nanoTime() - start1;

        long start2 = System.nanoTime();
        for (int i = 0; i < rounds; i++) {
            text.split(" ");
        }
        long cost2 = System.nanoTime() - start2;

        double ratio = cost2 == 0 ? 0D : (double) cost1 / (double) cost2;
        LOG.info("split-benchmark rounds={}, customNs={}, jdkNs={}, ratio(custom/jdk)={}", rounds, cost1, cost2, ratio);
    }
}
