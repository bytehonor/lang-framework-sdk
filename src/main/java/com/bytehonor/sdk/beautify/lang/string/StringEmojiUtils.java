package com.bytehonor.sdk.beautify.lang.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bytehonor.sdk.define.bytehonor.util.StringObject;

public class StringEmojiUtils {

    private static final String EMOJI = "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]";
    private static final Pattern EPATTERN_EMOJI = Pattern.compile(EMOJI,
            Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

    /**
     * 检测是否有emoji字符
     * 
     * @param source 需要判断的字符串
     * @return 一旦含有就抛出
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!notEmojiCharacter(codePoint)) {
                // 判断确认有表情字符
                return true;
            }
        }
        return false;
    }

    /**
     * 非emoji表情字符判断
     * 
     * @param ch
     * @return
     */
    private static boolean notEmojiCharacter(char ch) {
        return (ch == 0x0) || (ch == 0x9) || (ch == 0xA) || (ch == 0xD) || ((ch >= 0x20) && (ch <= 0xD7FF))
                || ((ch >= 0xE000) && (ch <= 0xFFFD)) || ((ch >= 0x10000) && (ch <= 0x10FFFF));
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     * 
     * @param text 需要过滤的字符串
     * @return
     */
    public static String removeEmoji(String text) {
        if (StringObject.isEmpty(text)) {
            return text;
        }
        // 两重判断
        Matcher emojiMatcher = EPATTERN_EMOJI.matcher(text);
        if (emojiMatcher.find()) {
            text = emojiMatcher.replaceAll("");
        }
        boolean hasEmoji = false;
        int len = text.length();
        char[] src = text.toCharArray();
        char[] arr = new char[len];
        int j = 0;
        for (int i = 0; i < len; i++) {
            if (!notEmojiCharacter(src[i])) {
                hasEmoji = true;
                continue;
            }
            arr[j++] = src[i];
        }
        if (!hasEmoji) {
            return text;
        }
        return new String(arr, 0, j);
    }

}
