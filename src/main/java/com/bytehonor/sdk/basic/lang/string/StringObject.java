package com.bytehonor.sdk.basic.lang.string;

public class StringObject {

    public static boolean isEmpty(String str) {
        return (str == null || "".equals(str));
    }

    public static boolean equals(String src1, String src2) {
        if (src1 == null || src2 == null) {
            return false;
        }
        return src1.equals(src2);
    }
}
