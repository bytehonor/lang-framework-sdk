package com.bytehonor.sdk.lang.spring.string;

import java.util.Objects;

import org.springframework.util.Base64Utils;

public class StringBase64Utils {

    public static String encodeSafe(String src) {
        Objects.requireNonNull(src, "src");
        if (SpringString.isEmpty(src)) {
            return "";
        }
        return encode(src).replaceAll("=", "~");
    }

    public static String decodeSafe(String base) {
        Objects.requireNonNull(base, "base");
        if (SpringString.isEmpty(base)) {
            return "";
        }
        base = base.replaceAll("~", "=");
        return decode(base);
    }

    public static String encode(String src) {
        Objects.requireNonNull(src, "src");
        if (SpringString.isEmpty(src)) {
            return "";
        }
        return Base64Utils.encodeToUrlSafeString(src.getBytes());
    }

    public static String decode(String base) {
        Objects.requireNonNull(base, "base");
        if (SpringString.isEmpty(base)) {
            return "";
        }
        return new String(Base64Utils.decodeFromUrlSafeString(base));
    }
}
