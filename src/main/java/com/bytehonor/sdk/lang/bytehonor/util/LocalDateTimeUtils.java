package com.bytehonor.sdk.lang.bytehonor.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import com.bytehonor.sdk.lang.bytehonor.constant.DateConstants;

public class LocalDateTimeUtils {

    public static long toTimestamp(LocalDateTime ldt) {
        Objects.requireNonNull(ldt, "ldt");
        return ldt.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public static long toTimestamp(String src) {
        return toTimestamp(src, DateConstants.YYYYMMDD_HHMMSS);
    }

    public static long toTimestamp(String src, DateTimeFormatter formatter) {
        Objects.requireNonNull(src, "src");
        Objects.requireNonNull(formatter, "formatter");
        LocalDateTime ldt = LocalDateTime.parse(src, formatter);
        return toTimestamp(ldt);
    }

    public static String format(LocalDateTime ldt) {
        return format(ldt, DateConstants.YYYYMMDD_HHMMSS);
    }

    public static String format(LocalDateTime ldt, DateTimeFormatter formatter) {
        Objects.requireNonNull(ldt, "ldt");
        Objects.requireNonNull(formatter, "formatter");
        return formatter.format(ldt);
    }

    public static String format(long time) {
        return format(time, DateConstants.YYYYMMDD_HHMMSS);
    }

    public static String format(long time, DateTimeFormatter formatter) {
        return format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()), formatter);
    }

    public static LocalDateTime fromTimestamp(long time) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
    }
}
