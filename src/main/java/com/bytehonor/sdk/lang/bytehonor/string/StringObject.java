package com.bytehonor.sdk.lang.bytehonor.string;

/**
 * @author lijianqiang
 *
 */
public class StringObject {

    public static boolean isEmpty(String str) {
        return (str == null || str.isEmpty());
    }

    public static boolean equals(String src1, String src2) {
        if (src1 == null || src2 == null) {
            return false;
        }
        return src1.equals(src2);
    }
}
