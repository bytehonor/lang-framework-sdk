package com.bytehonor.sdk.framework.lang.util;

import java.util.Objects;

/**
 * MD5 utils
 * 
 * @author lijianqiang
 *
 */
public class MD5Kit {

    public static String md5(String src) {
        Objects.requireNonNull(src, "src");
        return SimpleDigestKit.md5DigestAsHex(src.getBytes());
    }
}
