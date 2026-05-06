package com.bytehonor.sdk.framework.lang.match;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
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
 * 文本关键词匹配：将输入拆成“词”集合后，与多组 {@link WordMatcher} 比较。
 *
 * <p>
 * 支持的字符类别见 {@link #prepare(String)} / {@link #words(String)}：英文、数字、汉字及空格；
 * 英文与数字可混合为一段；纯汉字超过 {@value #CHINESE_MAX_LENGTH} 个字时会再切成更短片段参与匹配。
 * 参与匹配的词长度下限为 {@value #WORD_MIN_LENGTH}。
 * </p>
 *
 * <p>
 * 规则（组间均为 OR，组内为 AND，与 {@link WordMatcher} 一致）：
 * </p>
 * <ol>
 * <li>先判断排除：任一排除组的词全部出现在拆分结果中 → 整体不匹配；</li>
 * <li>再判断包含：任一包含组的词全部出现 → 匹配成功；否则不匹配。</li>
 * </ol>
 *
 * @author lijianqiang
 */
public class TextMatcher {

    private enum TokenType {
        WORD,
        CHINESE,
        INVALID
    }

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
        Objects.requireNonNull(excluders, "excluders");
        Objects.requireNonNull(includers, "includers");
        this.excluders = List.copyOf(excluders);
        this.includers = List.copyOf(includers);
    }

    public boolean match(String text) {
        if (StringKit.isEmpty(text)) {
            return false;
        }
        // 没有包含条件时，整体语义恒为“不匹配”
        if (CollectionUtils.isEmpty(this.includers)) {
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
        return "excluders:" + excluders.size() + ", includers:" + includers.size();
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
        text = text.toLowerCase(Locale.ROOT); // 全小写
        Set<String> raws = StringSplitUtils.toSet(text, CharConstants.BLANK);
        int size = raws.size();
        Set<String> result = new HashSet<String>(size * 10);
        for (String raw : raws) {
            if (raw.length() < WORD_MIN_LENGTH) {
                continue;
            }
            TokenType type = tokenType(raw);
            if (type == TokenType.WORD) {
                result.add(raw); // 英文、数字、英文数字, 直接使用
                continue;
            }
            if (type == TokenType.CHINESE) {
                addChineseParts(raw, result); // 汉字块二次分割
                continue;
            }
        }
        return result;
    }

    private static TokenType tokenType(String raw) {
        boolean hasLetter = false;
        boolean hasNumber = false;
        boolean hasChinese = false;
        int len = raw.length();
        for (int i = 0; i < len; i++) {
            char ch = raw.charAt(i);
            if (PatternKit.isLetterChar(ch)) {
                if (hasChinese) {
                    return TokenType.INVALID;
                }
                hasLetter = true;
                continue;
            }
            if (PatternKit.isNumberChar(ch)) {
                if (hasChinese) {
                    return TokenType.INVALID;
                }
                hasNumber = true;
                continue;
            }
            if (PatternKit.isChineseChar(ch)) {
                if (hasLetter || hasNumber) {
                    return TokenType.INVALID;
                }
                hasChinese = true;
                continue;
            }
            return TokenType.INVALID;
        }
        if (hasChinese) {
            if (!hasLetter && !hasNumber) {
                return TokenType.CHINESE;
            }
            return TokenType.INVALID;
        }
        if (hasLetter || hasNumber) {
            return TokenType.WORD;
        }
        return TokenType.INVALID;
    }

    private static void addChineseParts(String chinese, Set<String> result) {
        Set<String> parts = StringSliceUtils.slice(chinese, CHINESE_MAX_LENGTH);
        for (String part : parts) {
            if (part.length() < WORD_MIN_LENGTH) {
                continue;
            }
            result.add(part);
        }
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
                if (i > 0 && blank(source[i - 1], source[i])) {
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
        return PatternKit.isChineseChar(last) != PatternKit.isChineseChar(now);
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

            this.excluders.add(WordMatcher.of(words));
            return this;
        }

        public Builder include(String... words) {
            Objects.requireNonNull(words, "words");

            this.includers.add(WordMatcher.of(words));
            return this;
        }

        public TextMatcher build() {
            return new TextMatcher(excluders, includers);
        }
    }
}
