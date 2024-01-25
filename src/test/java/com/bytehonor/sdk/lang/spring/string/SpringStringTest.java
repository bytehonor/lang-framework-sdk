package com.bytehonor.sdk.lang.spring.string;

import static org.junit.Assert.*;

import org.junit.Test;

public class SpringStringTest {

    @Test
    public void testCamelToUnderline() {
        boolean ok1 = SpringString.camelToUnderline("AbbbCddd").equals("abbb_cddd");
        boolean ok2 = SpringString.camelToUnderline("Abbbcddd").equals("abbbcddd");
        boolean ok3 = SpringString.camelToUnderline("abbbC").equals("abbb_c");
        boolean ok4 = SpringString.camelToUnderline("abbbc").equals("abbbc");
        assertTrue("test", ok1 && ok2 && ok3 && ok4);
    }

    @Test
    public void testCamelToUnderline2() {
        boolean ok1 = SpringString.camelToUnderline("Ab_bbCddd").equals("ab_bb_cddd");
        boolean ok2 = SpringString.camelToUnderline("A!@#bbbcddd").equals("a!@#bbbcddd");
        boolean ok3 = SpringString.camelToUnderline("ab bbC").equals("ab bb_c");
        boolean ok4 = SpringString.camelToUnderline("abbbc").equals("abbbc");
        assertTrue("test", ok1 && ok2 && ok3 && ok4);
    }
}
