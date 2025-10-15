package com.bytehonor.sdk.framework.lang.match;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.framework.lang.exception.SpringLangException;
import com.bytehonor.sdk.framework.lang.string.SpringString;
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

    private final Set<String> matchers;

    private WordMatcher(Set<String> matchers) {
        this.matchers = matchers;
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

        Set<String> matchers = new HashSet<String>();
        for (String word : words) {
            if (SpringString.isEmpty(word)) {
                continue;
            }
            matchers.add(format(word));
        }

        return new WordMatcher(matchers);
    }

    public static String format(String word) {
        Objects.requireNonNull(word, "word");

        String val = word.trim();
        if (SpringString.isEmpty(val)) {
            throw new SpringLangException("word cannt be empty");
        }
        return val.toLowerCase();
    }

    public boolean match(Set<String> words) {
        if (CollectionUtils.isEmpty(words)) {
            return false;
        }

        for (String word : matchers) {
            if (words.contains(word) == false) {
                return false;
            }
        }
        return true;
    }

    public Set<String> getWords() {
        return matchers;
    }

    @Override
    public String toString() {
        return JoinUtils.strings(matchers);
    }

}
