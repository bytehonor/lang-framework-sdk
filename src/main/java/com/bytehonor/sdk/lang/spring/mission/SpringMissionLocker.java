package com.bytehonor.sdk.lang.spring.mission;

public interface SpringMissionLocker {

    public boolean lock(String key, String value, long millis);

    public String get(String key);

    public void expireAt(String key, long timestamp);
}
