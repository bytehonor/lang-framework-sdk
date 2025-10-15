package com.bytehonor.sdk.framework.lang.string;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Objects;

public class StringBase64Utils {

    private static final Encoder ENCODER = Base64.getUrlEncoder();
    private static final Decoder DECODER = Base64.getUrlDecoder();

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
        // return Base64Utils.encodeToUrlSafeString(src.getBytes());
        return ENCODER.encodeToString(src.getBytes());
    }

    public static String decode(String base) {
        Objects.requireNonNull(base, "base");
        if (SpringString.isEmpty(base)) {
            return "";
        }
        // return new String(Base64Utils.decodeFromUrlSafeString(base));
        return new String(DECODER.decode(base));
    }
}
