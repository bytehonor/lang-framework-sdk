package com.bytehonor.sdk.framework.lang.string;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * URL 编解码工具（固定使用 UTF-8，编码时将 {@code +} 替换为 {@code %20}）。
 *
 * @author lijianqiang
 */
public class StringCodeUtils {

    public static final String UTF_8 = "UTF-8";

    /**
     * 使用 UTF-8 对 {@code src} 做 {@link URLDecoder} 解码。
     */
    public static String urlDecode(String src) throws UnsupportedEncodingException {
        return URLDecoder.decode(src, UTF_8);
    }

    /**
     * 使用 UTF-8 对 {@code src} 做 {@link URLEncoder} 编码，并把空格对应的 {@code +} 替换为 {@code %20}。
     */
    public static String urlEncode(String src) throws UnsupportedEncodingException {
        // 用URLEncoder.encode方法会把空格变成加号（+）,encode之后在替换一下
        return URLEncoder.encode(src, UTF_8).replace("+", "%20");
    }
}
