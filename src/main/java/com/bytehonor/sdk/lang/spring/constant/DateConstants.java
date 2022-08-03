package com.bytehonor.sdk.lang.spring.constant;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateConstants {

    private final static Locale CN = Locale.CHINA;

    public final static DateTimeFormatter HHMM = DateTimeFormatter.ofPattern("HH:mm", CN);

    public final static DateTimeFormatter HHMMSS = DateTimeFormatter.ofPattern("HH:mm:ss", CN);

    public final static DateTimeFormatter YYYYMMDD = DateTimeFormatter.ofPattern("yyyy-MM-dd", CN);

    public final static DateTimeFormatter YYYY_MM_DD_HH = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH", CN);

    public final static DateTimeFormatter YYYY_MM_DD_HH_MM = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm", CN);

    public final static DateTimeFormatter YYYYMMDD_HHMM = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", CN);

    public final static DateTimeFormatter YYYYMMDD_HHMMSS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", CN);

    public final static DateTimeFormatter CN_YYYYMMDD = DateTimeFormatter.ofPattern("yyyy年MM月dd日", CN);

    public final static DateTimeFormatter CN_YYYYMMDD_HH = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时", CN);

    public final static DateTimeFormatter CN_YYYYMMDD_HHMM = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分", CN);

    public final static DateTimeFormatter CN_YYYYMMDD_HHMMSS = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒", CN);

    public final static DateTimeFormatter CN_YYYYMMDDHH = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时", CN);

    public final static DateTimeFormatter CN_YYYYMMDDHHMM = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时mm分", CN);

    public final static DateTimeFormatter CN_YYYYMMDDHHMMSS = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时mm分ss秒", CN);

    public final static DateTimeFormatter CN_MMDDHH = DateTimeFormatter.ofPattern("MM月dd日HH时", CN);

    public final static DateTimeFormatter CN_MMDDHHMM = DateTimeFormatter.ofPattern("MM月dd日HH时mm分", CN);

    public final static DateTimeFormatter CN_MMDDHHMMSS = DateTimeFormatter.ofPattern("MM月dd日HH时mm分ss秒", CN);

    public final static DateTimeFormatter CN_MDHH = DateTimeFormatter.ofPattern("M月d日HH时", CN);

    public final static DateTimeFormatter CN_MDHHMM = DateTimeFormatter.ofPattern("M月d日HH时mm分", CN);

    public final static DateTimeFormatter CN_MDHHMMSS = DateTimeFormatter.ofPattern("M月d日HH时mm分ss秒", CN);
}
