package com.bytehonor.sdk.lang.spring.match;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.lang.spring.exception.SpringLangException;
import com.bytehonor.sdk.lang.spring.string.StringObject;
import com.bytehonor.sdk.lang.spring.util.JoinUtils;

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

    /**
     * 统一转换成小写
     * 
     * @param words
     * @return
     */
    public static WordMatcher of(String... words) {
        Objects.requireNonNull(words, "wirds");

        Set<String> matchers = new HashSet<String>();
        for (String word : words) {
            if (StringObject.isEmpty(word)) {
                continue;
            }
            matchers.add(format(word));
        }

        return new WordMatcher(matchers);
    }

    public static String format(String word) {
        Objects.requireNonNull(word, "word");

        String val = word.trim();
        if (StringObject.isEmpty(val)) {
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
