package com.bytehonor.sdk.lang.bytehonor.string;

import java.util.Objects;

import org.springframework.util.Base64Utils;

public class StringBase64Utils {

    public static String base64Encode(String src) {
        Objects.requireNonNull(src, "src");
        String base = Base64Utils.encodeToUrlSafeString(src.getBytes());
        base = base.replaceAll("=", "_");
        return base;
    }

    public static String base64Decode(String base) {
        Objects.requireNonNull(base, "base");
        base = base.replaceAll("_", "=");
        return new String(Base64Utils.decodeFromUrlSafeString(base));
    }
}
