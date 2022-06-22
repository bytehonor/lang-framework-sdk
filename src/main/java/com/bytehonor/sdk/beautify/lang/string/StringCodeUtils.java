package com.bytehonor.sdk.beautify.lang.string;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class StringCodeUtils {

    public static final String UTF_8 = "UTF-8";

    public static String urlDecode(String src) throws UnsupportedEncodingException {
        return URLDecoder.decode(src, UTF_8);
    }

    public static String urlEncode(String src) throws UnsupportedEncodingException {
        // 用URLEncoder.encode方法会把空格变成加号（+）,encode之后在替换一下
        return URLEncoder.encode(src, UTF_8).replace("+", "%20");
    }
}
