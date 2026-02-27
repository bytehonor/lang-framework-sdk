package com.bytehonor.sdk.framework.lang.match;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.framework.lang.constant.CharConstants;
import com.bytehonor.sdk.framework.lang.string.StringKit;
import com.bytehonor.sdk.framework.lang.string.StringRemoveUtils;
import com.bytehonor.sdk.framework.lang.string.StringSliceUtils;
import com.bytehonor.sdk.framework.lang.string.StringSplitUtils;
import com.bytehonor.sdk.framework.lang.util.PatternKit;

/**
 * <pre>
 * 支持中文,英文,数字
 * 
 * 优先排除, 其次满足. 排除或满足都只需要命中一组即可
 * 
 * 长度最小2，最大4
 * 
 * </pre>
 * 
 * @author lijianqiang
 *
 */
public class TextMatcher {

    private static final int WORD_MIN_LENGTH = 2;

    private static final int CHINESE_MAX_LENGTH = 4;

    /**
     * 排除的条件, 满足一组就排除 or
     */
    private final List<WordMatcher> excluders;

    /**
     * 满足的条件, 满足一组就可以 or
     */
    private final List<WordMatcher> includers;

    public TextMatcher(List<WordMatcher> excluders, List<WordMatcher> includers) {
        this.excluders = excluders;
        this.includers = includers;
    }

    public boolean match(String text) {
        if (StringKit.isEmpty(text)) {
            return false;
        }

        Set<String> words = words(text);
        if (CollectionUtils.isEmpty(words)) {
            return false;
        }

        if (doMatch(words, this.excluders)) {
            return false;
        }
        return doMatch(words, this.includers);
    }

    public List<WordMatcher> getExcluders() {
        return excluders;
    }

    public List<WordMatcher> getIncluders() {
        return includers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("excluders:").append(excluders.size()).append(", includers:").append(includers.size());
        return sb.toString();
    }

    /**
     * <pre>
     * 全小写
     * 
     * 非中文按空格自然分割, 中文超过4个字的重新切割成4字以下的字块
     * </pre>
     * 
     * @param text
     * @return
     */
    public static Set<String> words(String text) {
        // 只保留 英文,数字,汉字,空格
        text = prepare(text);
        if (StringKit.isEmpty(text)) {
            return new HashSet<String>();
        }
        text = text.toLowerCase(); // 全小写
        Set<String> raws = StringSplitUtils.toSet(text, CharConstants.BLANK);
        int size = raws.size();
        Set<String> result = new HashSet<String>(size * 10);
        Set<String> chineseWords = new HashSet<String>(size * 10);
        for (String raw : raws) {
            if (raw.length() < WORD_MIN_LENGTH) {
                continue;
            }

            if (PatternKit.isLetter(raw)) {
                result.add(raw); // 纯英文,直接使用, 且小写
                continue;
            }
            if (PatternKit.isNumber(raw)) {
                result.add(raw); // 纯数字,直接使用
                continue;
            }
            if (PatternKit.isLetterNumber(raw)) {
                result.add(raw); // 英文数字,直接使用
                continue;
            }
            if (PatternKit.isChinese(raw)) {
                chineseWords.add(raw); // 汉字块二次分割
                continue;
            }
        }
        if (CollectionUtils.isEmpty(chineseWords) == false) {
            for (String chinese : chineseWords) {
                if (chinese.length() < WORD_MIN_LENGTH) {
                    continue;
                }
                Set<String> parts = StringSliceUtils.slice(chinese, CHINESE_MAX_LENGTH);
                for (String part : parts) {
                    if (part.length() < WORD_MIN_LENGTH) {
                        continue;
                    }
                    result.add(part);
                }
            }
        }
        return result;
    }

    /**
     * 只保留 英文,汉字,数字,空格 4种. 非中文之间插入空格
     * 
     * @param text
     * @return
     */
    public static String prepare(String text) {
        if (StringKit.isEmpty(text)) {
            return "";
        }
        text = StringRemoveUtils.removeHttp(text);
        text = StringRemoveUtils.replaceNonNormalWithBlank(text);
        if (StringKit.isEmpty(text)) {
            return "";
        }
        int length = text.length();
        if (length < WORD_MIN_LENGTH) {
            return text;
        }
        char[] source = text.toCharArray();
        char[] target = new char[length * 2];
        int at = 0;
        for (int i = 0; i < length; i++) {
            if (PatternKit.isNormalChar(source[i])) {
                if (i > 1 && blank(source[i - 1], source[i])) {
                    // 非中文之间补个空格
                    target[at++] = CharConstants.BLANK;
                }
                target[at++] = source[i];
                continue;
            }
            target[at++] = CharConstants.BLANK;
        }
        return new String(target, 0, at);
    }

    private static boolean blank(char last, char now) {
        if (last == CharConstants.BLANK || now == CharConstants.BLANK) {
            return false;
        }
        if (PatternKit.isChineseChar(last) && PatternKit.isChineseChar(now) == false) {
            return true;
        }
        if (PatternKit.isChineseChar(last) == false && PatternKit.isChineseChar(now)) {
            return true;
        }
        return false;
    }

    private static boolean doMatch(Set<String> words, List<WordMatcher> matchers) {
        if (CollectionUtils.isEmpty(words) || CollectionUtils.isEmpty(matchers)) {
            return false;
        }

        for (WordMatcher matcher : matchers) {
            if (matcher.match(words)) {
                return true;
            }
        }
        return false;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final List<WordMatcher> excluders;

        private final List<WordMatcher> includers;

        private Builder() {
            this.excluders = new ArrayList<WordMatcher>();
            this.includers = new ArrayList<WordMatcher>();
        }

        public Builder exclude(String... words) {
            Objects.requireNonNull(words, "words");

            this.excluders.add(WordMatcher.of(Arrays.asList(words)));
            return this;
        }

        public Builder include(String... words) {
            Objects.requireNonNull(words, "words");

            this.includers.add(WordMatcher.of(Arrays.asList(words)));
            return this;
        }

        public TextMatcher build() {
            return new TextMatcher(excluders, includers);
        }
    }
}
