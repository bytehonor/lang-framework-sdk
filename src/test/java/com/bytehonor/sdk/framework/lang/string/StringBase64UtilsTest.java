package com.bytehonor.sdk.framework.lang.string;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringBase64UtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(StringBase64UtilsTest.class);

    @Test
    public void test() {
        String enc = "eyJwbGF0Zm9ybSI6InBjIiwibm9uY2UiOiIybVlBem4iLCJ0aW1lc3RhbXAiOjE2NTM2MTgzNjk5ODAsInNpZyI6IjFkNGQwMDg4ZDRkMmJmMDQ3MTA3ZTNlZDZmYTM0MTZkIn0=";
        String target = "{\"platform\":\"pc\",\"nonce\":\"2mYAzn\",\"timestamp\":1653618369980,\"sig\":\"1d4d0088d4d2bf047107e3ed6fa3416d\"}";
        String dec = StringBase64Utils.decode(enc);

        LOG.info("dec:{}", dec);
        assertTrue("test", target.equals(dec));
    }

    @Test
    public void test2() {
        String word = "佩洛西";// "5L2p5rSb6KW_"
        String base = StringBase64Utils.encodeSafe(word);
        String word1 = StringBase64Utils.decodeSafe(base);
        LOG.info("test2: {}, {}, {}", word, base, word1);
        assertTrue("test2", word.equals(word1));
    }

    @Test
    public void test3() {
        List<String> words = new ArrayList<String>();
        words.add("中文");
        words.add("中文abc");
        words.add("中文abc_+");
        words.add("abced");
        words.add("比特币");
        words.add("挖矿");
        words.add("加密货币");
        
        int success = 0;
        for (String word : words) {
            String base = StringBase64Utils.encodeSafe(word);
            String word1 = StringBase64Utils.decodeSafe(base);
            LOG.info("test3 {}, {}, {}", word, base, word1);
            if (word.equals(word1)) {
                success++;
            }
        }

        assertTrue("test", words.size() == success);
    }
}
