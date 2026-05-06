package com.bytehonor.sdk.framework.lang.match;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.framework.lang.exception.SpringLangException;
import com.bytehonor.sdk.framework.lang.string.StringKit;
import com.bytehonor.sdk.framework.lang.util.JoinUtils;

/**
 * <pre>
 * 一律转为小写进行匹配
 * 
 * 英文 汉字 数字 3种
 * </pre>
 * 
 * @author lijianqiang
 *
 */
public class WordMatcher {

    private final Set<String> targets;

    private WordMatcher(Set<String> targets) {
        this.targets = targets;
    }

    public static WordMatcher of(String... words) {
        return of(Arrays.asList(words));
    }

    /**
     * 统一转换成小写
     * 
     * @param words
     * @return
     */
    public static WordMatcher of(List<String> words) {
        Objects.requireNonNull(words, "words");
        if (CollectionUtils.isEmpty(words)) {
            throw new SpringLangException("words cannt be empty");
        }

        Set<String> formats = new HashSet<String>(words.size());
        for (String word : words) {
            if (StringKit.isEmpty(word)) {
                continue;
            }
            formats.add(format(word));
        }
        if (CollectionUtils.isEmpty(formats)) {
            throw new SpringLangException("words cannt be empty");
        }

        return new WordMatcher(Collections.unmodifiableSet(formats));
    }

    public static String format(String word) {
        Objects.requireNonNull(word, "word");

        String val = word.trim();
        if (StringKit.isEmpty(val)) {
            throw new SpringLangException("word cannt be empty");
        }
        return val.toLowerCase(Locale.ROOT);
    }

    public boolean match(Set<String> words) {
        if (CollectionUtils.isEmpty(words) || CollectionUtils.isEmpty(targets)) {
            return false;
        }

        for (String target : targets) {
            if (words.contains(target) == false) {
                return false;
            }
        }
        return true;
    }

    public Set<String> getWords() {
        return targets;
    }

    @Override
    public String toString() {
        return JoinUtils.strings(targets);
    }

}
