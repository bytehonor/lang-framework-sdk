package com.bytehonor.sdk.framework.lang.string;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.constant.StringConstants;

/**
 * <pre>
 * 字符串提取
 * </pre>
 * 
 * @author lijianqiang
 *
 */
public class StringExtractUtils {

    private static Logger LOG = LoggerFactory.getLogger(StringExtractUtils.class);

    public static final char IGNORE_CHAR = '*';

    private static final String EMPTY = StringConstants.EMPTY;

    /**
     * <pre>
     * 截取一段, 一定是从开头到结尾, 即使先查到结尾
     * 前后保留 header 和 footer
     * 支持*号做模糊匹配, 如he*der
     * </pre>
     * 
     * @param src
     * @param header
     * @param footer
     * @return
     */
    public static String extract(String src, String header, String footer) {
        return doExtract(src, header, footer, false);
    }

    /**
     * <pre>
     * 截取一段, 一定是从开头到结尾, 即使先查到结尾
     * 前后剔除 header 和 footer
     * 支持*号做模糊匹配, 如he*der
     * </pre>
     * 
     * @param src
     * @param header
     * @param footer
     * @return
     */
    public static String extractTrim(String src, String header, String footer) {
        return doExtract(src, header, footer, true);
    }

    private static String doExtract(String src, String header, String footer, boolean trim) {
        Objects.requireNonNull(src, "src");
        Objects.requireNonNull(header, "header");
        Objects.requireNonNull(footer, "footer");

        if (SpringString.isEmpty(src)) {
            return EMPTY;
        }
        if (SpringString.isEmpty(header) || SpringString.isEmpty(footer)) {
            LOG.warn("header:{} or footer:{} empty");
            return EMPTY;
        }
        final int length = src.length();
        final char[] source = src.toCharArray();
        final int headerSize = header.length();
        final char[] headers = header.toCharArray();
        final int footerSize = footer.length();
        final char[] footers = footer.toCharArray();

        boolean findheader = false;
        int beginAt = 0;
        int i = 0;
        int j = 0;
        int k = 0;
        for (i = 0; i < length; i++) {
            if (source[i] != headers[0]) {
                continue;
            }
            for (j = 0, k = i + j; j < headerSize && k < length; j++, k++) {
                if (IGNORE_CHAR == headers[j]) {
                    continue;
                }
                if (source[k] != headers[j]) {
                    break;
                }
            }
            if (j == headerSize) {
                findheader = true;
                beginAt = i;
                break;
            }
        }

        LOG.debug("findheader:{}, beginAt:{}, i:{}", findheader, beginAt, i);
        if (findheader == false) {
            return EMPTY;
        }

        boolean findFooter = false;
        int count = 0;
        for (; i < length; i++) {
            count++;
            if (source[i] != footers[0]) {
                continue;
            }
            for (j = 0, k = i + j; j < footerSize && k < length; j++, k++) {
                if (IGNORE_CHAR == footers[j]) {
                    continue;
                }
                if (source[k] != footers[j]) {
                    break;
                }
            }
            if (j == footerSize) {
                count += (footerSize - 1);
                findFooter = true;
                break;
            }
        }
        if (findheader && findFooter) {
            if (trim) {
                beginAt = beginAt + headerSize;
                count = count - footerSize - headerSize;
            }
            // LOG.debug("beginAt:{}, count:{}", beginAt, count);
            return new String(source, beginAt, count);
        }
        return EMPTY;
    }
}
