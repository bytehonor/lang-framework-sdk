package com.bytehonor.sdk.framework.lang.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.bytehonor.sdk.framework.lang.string.SpringString;

public class KeyValueMap {

    private final Map<String, String> map;

    public KeyValueMap() {
        this.map = new HashMap<String, String>();
    }

    public static KeyValueMap create() {
        return new KeyValueMap();
    }

    public KeyValueMap put(String key, Integer value) {
        Objects.requireNonNull(key, "key");
        if (value != null) {
            this.map.put(key, value.toString());
        }
        return this;
    }

    public KeyValueMap put(String key, Long value) {
        Objects.requireNonNull(key, "key");
        if (value != null) {
            this.map.put(key, value.toString());
        }
        return this;
    }

    public KeyValueMap put(String key, Boolean value) {
        Objects.requireNonNull(key, "key");
        if (value != null) {
            this.map.put(key, value.toString());
        }
        return this;
    }

    public KeyValueMap put(String key, Double value) {
        Objects.requireNonNull(key, "key");
        if (value != null) {
            this.map.put(key, value.toString());
        }
        return this;
    }

    public KeyValueMap put(String key, String value) {
        Objects.requireNonNull(key, "key");
        if (SpringString.isEmpty(value) == false) {
            this.map.put(key, value);
        }
        return this;
    }

    public String get(String key) {
        Objects.requireNonNull(key, "key");
        return map.get(key);
    }

    public boolean has(String key) {
        Objects.requireNonNull(key, "key");
        return map.containsKey(key);
    }

    public Map<String, String> map() {
        return map;
    }

}
