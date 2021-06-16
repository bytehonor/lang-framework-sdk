package com.bytehonor.sdk.lang.bytehonor.string;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Objects;

public class StringCodeUtils {

    public static final String UTF_8 = "UTF-8";

    private static final Encoder ENC = Base64.getEncoder();

    private static final Decoder DEC = Base64.getDecoder();

    private static final Charset CHARSET = Charset.forName(UTF_8);

    public static String base64Encode(String src) {
        Objects.requireNonNull(src, "src");
        return ENC.encodeToString(src.getBytes(CHARSET));
    }

    public static String base64Decode(String src) {
        Objects.requireNonNull(src, "src");
        byte[] base64decodedBytes = DEC.decode(src);
        return new String(base64decodedBytes, CHARSET);
    }

    public static String urlDecode(String src) throws UnsupportedEncodingException {
        return URLDecoder.decode(src, UTF_8);
    }

    public static String urlEncode(String src) throws UnsupportedEncodingException {
        // 用URLEncoder.encode方法会把空格变成加号（+）,encode之后在替换一下
        return URLEncoder.encode(src, UTF_8).replace("+", "%20");
    }
}
