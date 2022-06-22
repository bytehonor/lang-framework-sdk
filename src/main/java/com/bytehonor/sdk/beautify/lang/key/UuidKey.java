package com.bytehonor.sdk.beautify.lang.key;

import com.bytehonor.sdk.define.bytehonor.util.RandomUtils;
import com.bytehonor.sdk.define.bytehonor.util.StringObject;

public class UuidKey {

    private String uuid;

    private Long time;

    public static UuidKey create(String uuid, long time) {
        UuidKey uk = new UuidKey();
        uk.setUuid(uuid);
        uk.setTime(time);
        return uk;
    }

    public static String toHex(String uuid) {
        UuidKey uk = create(uuid, System.currentTimeMillis());
        return toHex(uk);
    }

    public static String toHex(String uuid, long time) {
        UuidKey uk = create(uuid, time);
        return toHex(uk);
    }

    public static String toHex(UuidKey uk) {
        // 4 + 32 + time
        String hex = new StringBuilder().append(RandomUtils.getInt(1111, 9999)).append(reverse(uk.getUuid()))
                .append(Long.toHexString(uk.getTime())).toString();
        return hex;
    }

    public static UuidKey fromHex(String hex) {
        if (StringObject.isEmpty(hex) || hex.length() < 39) {
            throw new RuntimeException("invalid hex");
        }

        // 有4个随机字符
        String uuid = hex.substring(4, 36);
        String time = hex.substring(36);

        UuidKey uk = new UuidKey();
        uk.setUuid(reverse(uuid));
        uk.setTime(Long.parseLong(time, 16));
        return uk;
    }

    private static String reverse(String src) {
        StringBuilder sb = new StringBuilder().append(src).reverse();
        return sb.toString();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
