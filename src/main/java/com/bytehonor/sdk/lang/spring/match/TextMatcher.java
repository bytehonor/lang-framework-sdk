package com.bytehonor.sdk.lang.spring.match;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.define.spring.constant.CharConstants;
import com.bytehonor.sdk.lang.spring.nlp.TextNlpUtils;
import com.bytehonor.sdk.lang.spring.string.StringRemoveUtils;
import com.bytehonor.sdk.lang.spring.string.StringSplitUtils;
import com.bytehonor.sdk.lang.spring.util.StringObject;

/**
 * 优先排除, 其次满足. 排除或满足都只需要命中一组即可
 * 
 * @author lijianqiang
 *
 */
public class TextMatcher {

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
        if (StringObject.isEmpty(text)) {
            return false;
        }

        Set<String> words = StringSplitUtils.toSet(prepare(text), CharConstants.BLANK);
        if (contains(words, this.excluders)) {
            return false;
        }
        return contains(words, this.includers);
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
     * 只保留 英文,数字,汉字,空格
     * 
     * @param text
     * @return
     */
    private static String prepare(String text) {
        if (StringObject.isEmpty(text)) {
            return "";
        }

        text = TextNlpUtils.removeHttp(text);
        return StringRemoveUtils.replaceNonNormalWithBlank(text).toLowerCase();
    }

    public static boolean contains(Set<String> words, List<WordMatcher> matchers) {
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
