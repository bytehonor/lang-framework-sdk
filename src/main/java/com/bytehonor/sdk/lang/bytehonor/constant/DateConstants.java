package com.bytehonor.sdk.lang.bytehonor.constant;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateConstants {

    public final static DateTimeFormatter HHMM = DateTimeFormatter.ofPattern("HH:mm", Locale.CHINA);

    public final static DateTimeFormatter HHMMSS = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.CHINA);

    public final static DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.CHINA);

    public final static DateTimeFormatter YYYY_MM_DD_HH = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH", Locale.CHINA);

    public final static DateTimeFormatter YYYYMMDD_HHMM = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.CHINA);

    public final static DateTimeFormatter YYYYMMDD_HHMMSS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss",
            Locale.CHINA);

    public final static DateTimeFormatter YYYYMMDD_HHMMSSZ = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z",
            Locale.CHINA);

    public final static DateTimeFormatter CN_YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy年MM月dd日", Locale.CHINA);

    public final static DateTimeFormatter CN_YYYYMMDD_HHMM = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm",
            Locale.CHINA);
}
