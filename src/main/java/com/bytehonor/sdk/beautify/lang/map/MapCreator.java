package com.bytehonor.sdk.beautify.lang.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MapCreator {

    private Map<String, String> map;

    public MapCreator() {
        this.map = new HashMap<String, String>();
    }

    public static MapCreator create() {
        return new MapCreator();
    }

    public MapCreator put(String key, Integer value) {
        Objects.requireNonNull(key, "key");
        if (value != null) {
            this.map.put(key, value.toString());
        }
        return this;
    }

    public MapCreator put(String key, Long value) {
        Objects.requireNonNull(key, "key");
        if (value != null) {
            this.map.put(key, value.toString());
        }
        return this;
    }

    public MapCreator put(String key, String value) {
        Objects.requireNonNull(key, "key");
        if (value != null) {
            this.map.put(key, value);
        }
        return this;
    }

    public Map<String, String> toMap() {
        return map;
    }

}
