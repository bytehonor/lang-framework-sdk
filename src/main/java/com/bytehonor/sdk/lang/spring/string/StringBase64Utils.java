package com.bytehonor.sdk.lang.spring.string;

import java.util.Objects;

import org.springframework.util.Base64Utils;

public class StringBase64Utils {

    public static String encodeSafe(String src) {
        Objects.requireNonNull(src, "src");
        return encode(src).replaceAll("=", "_");
    }

    public static String decodeSafe(String base) {
        Objects.requireNonNull(base, "base");
        base = base.replaceAll("_", "=");
        return decode(base);
    }

    public static String encode(String src) {
        Objects.requireNonNull(src, "src");
        return Base64Utils.encodeToUrlSafeString(src.getBytes());
    }

    public static String decode(String base) {
        Objects.requireNonNull(base, "base");
        return new String(Base64Utils.decodeFromUrlSafeString(base));
    }
}
