package com.bytehonor.sdk.lang.bytehonor.string;

import static org.junit.Assert.*;

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

}
