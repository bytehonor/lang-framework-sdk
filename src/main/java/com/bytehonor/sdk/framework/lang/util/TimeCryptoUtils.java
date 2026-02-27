package com.bytehonor.sdk.framework.lang.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.core.Randomizer;
import com.bytehonor.sdk.framework.lang.getter.IntegerGetter;
import com.bytehonor.sdk.framework.lang.string.StringKit;

public class TimeCryptoUtils {

    private static final Logger LOG = LoggerFactory.getLogger(TimeCryptoUtils.class);

    private static final String BITS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final int LENGTH = BITS.length();

    private static final int YEAR = 2018;

    private static final long DIFF = 20180328L;

    public static String toHex(long timestamp) {
        return StringKit.reverse(Long.toString(timestamp - DIFF, 36));
    }

    public static long fromHex(String hex) {
        return Long.parseLong(StringKit.reverse(hex), 36) + DIFF;
    }

    public static String encode() {
        return encode(LocalDateTime.now());
    }

    public static String encode(long timestamp) {
        return encode(TimeKit.fromTimestamp(timestamp));
    }

    public static String encode(LocalDateTime ldt) {
        Objects.requireNonNull(ldt, "ldt");

        int rand = Randomizer.integer(111, 678); // 3
        int year = ldt.getYear() - YEAR; // 1
        int month = ldt.getMonthValue(); // 1
        int day = ldt.getDayOfMonth(); // 1
        int hour = ldt.getHour();// 1
        int minute = ldt.getMinute();// 1
        int second = ldt.getSecond(); // 1
        int sum = rand + year + month + day + hour + minute + second; // 3
        // s4 + 3 + s4 + 1 + 1 + 1 + s4 + 1 + 1 + 1 + s4 + 3 + s4
        return new StringBuilder().append(randstr(4)).append(sum).append(randstr(4)).append(toChar(second))
                .append(toChar(minute)).append(toChar(hour)).append(randstr(4)).append(toChar(day))
                .append(toChar(month)).append(toChar(year)).append(randstr(4)).append(rand).append(randstr(4))
                .toString();
    }

    private static String randstr(int len) {
        return Randomizer.string(4);
    }

    private static char toChar(int at) {
        if (at > LENGTH) {
            return '*';
        }
        return BITS.charAt(at);
    }

    private static int toInt(char c) {
        return BITS.indexOf(c);
    }

    public static LocalDateTime decode(String src) {
        if (src == null || 32 != src.length()) {
            return null;
        }

        int sum = IntegerGetter.optional(src.substring(4, 7), 0);
        int second = toInt(src.charAt(11));
        int minute = toInt(src.charAt(12));
        int hour = toInt(src.charAt(13));
        int day = toInt(src.charAt(18));
        int month = toInt(src.charAt(19));
        int year = toInt(src.charAt(20));
        int rand = IntegerGetter.optional(src.substring(25, 28), 0);
        LOG.debug("sum:{}, {} + {}-{}-{} {}:{}:{}", sum, rand, year, month, day, hour, minute, second);
        if (sum != (rand + second + minute + hour + day + month + year)) {
            return null;
        }
        return LocalDateTime.of(LocalDate.of(YEAR + year, month, day), LocalTime.of(hour, minute, second));
    }
}
