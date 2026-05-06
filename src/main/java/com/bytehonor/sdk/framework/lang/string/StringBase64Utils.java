package com.bytehonor.sdk.framework.lang.string;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Objects;

/**
 * 基于 JDK {@link Base64} URL 安全编解码的字符串工具（{@link #encodeSafe(String)} 将填充符 {@code =} 替换为 {@code ~}）。
 *
 * @author lijianqiang
 */
public class StringBase64Utils {

    private static final Encoder ENCODER = Base64.getUrlEncoder();
    private static final Decoder DECODER = Base64.getUrlDecoder();

    /**
     * URL 安全 Base64 编码后将 {@code =} 替换为 {@code ~}，便于在部分场景下安全传输。
     */
    public static String encodeSafe(String src) {
        Objects.requireNonNull(src, "src");
        if (StringKit.isEmpty(src)) {
            return "";
        }
        return encode(src).replaceAll("=", "~");
    }

    /**
     * 先将 {@code ~} 还原为 {@code =}，再执行 URL 安全 Base64 解码。
     */
    public static String decodeSafe(String base) {
        Objects.requireNonNull(base, "base");
        if (StringKit.isEmpty(base)) {
            return "";
        }
        base = base.replaceAll("~", "=");
        return decode(base);
    }

    /**
     * 对非空字符串做 URL 安全 Base64 编码；空串返回空串。
     */
    public static String encode(String src) {
        Objects.requireNonNull(src, "src");
        if (StringKit.isEmpty(src)) {
            return "";
        }
        // return Base64Utils.encodeToUrlSafeString(src.getBytes());
        return ENCODER.encodeToString(src.getBytes());
    }

    /**
     * 对非空 Base64 串做 URL 安全解码为原始字符串；空串返回空串。
     */
    public static String decode(String base) {
        Objects.requireNonNull(base, "base");
        if (StringKit.isEmpty(base)) {
            return "";
        }
        // return new String(Base64Utils.decodeFromUrlSafeString(base));
        return new String(DECODER.decode(base));
    }
}
