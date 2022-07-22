package com.bytehonor.sdk.lang.spring.nlp;

import com.bytehonor.sdk.define.spring.constant.CharConstants;
import com.bytehonor.sdk.lang.spring.string.StringRemoveUtils;
import com.bytehonor.sdk.lang.spring.util.StringObject;

public class TextNlpUtils {

    private static final String HTTP = "http";

    private static final String HTTP_PRE = "http://";

    private static final String HTTPS_PRE = "https://";

    public static String removeHttp(String src) {
        if (StringObject.isEmpty(src)) {
            return "";
        }
        int begin = src.indexOf(HTTP);
        if (begin < 0) {
            return src;
        }
        begin = src.indexOf(HTTP_PRE);
        if (begin < 0) {
            begin = src.indexOf(HTTPS_PRE);
        }
        if (begin < 0) {
            return src;
        }
        int end = src.indexOf(CharConstants.BLANK, begin);
        if (end < 0) {
            end = src.length();
        }

        String res = StringRemoveUtils.remove(src, begin, end);

        begin = res.indexOf(HTTP_PRE);
        if (begin < 0) {
            begin = res.indexOf(HTTPS_PRE);
        }
        if (begin < 0) {
            return res;
        }
        return removeHttp(res);
    }
}
