package com.bytehonor.sdk.framework.lang.string;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringKitTest {

    @Test
    public void testCamelToUnderline() {
        boolean ok1 = StringKit.camelToUnderline("AbbbCddd").equals("abbb_cddd");
        boolean ok2 = StringKit.camelToUnderline("Abbbcddd").equals("abbbcddd");
        boolean ok3 = StringKit.camelToUnderline("abbbC").equals("abbb_c");
        boolean ok4 = StringKit.camelToUnderline("abbbc").equals("abbbc");
        assertTrue("test", ok1 && ok2 && ok3 && ok4);
    }

    @Test
    public void testCamelToUnderline2() {
        boolean ok1 = StringKit.camelToUnderline("Ab_bbCddd").equals("ab_bb_cddd");
        boolean ok2 = StringKit.camelToUnderline("A!@#bbbcddd").equals("a!@#bbbcddd");
        boolean ok3 = StringKit.camelToUnderline("ab bbC").equals("ab bb_c");
        boolean ok4 = StringKit.camelToUnderline("abbbc").equals("abbbc");
        assertTrue("test", ok1 && ok2 && ok3 && ok4);
    }
}
