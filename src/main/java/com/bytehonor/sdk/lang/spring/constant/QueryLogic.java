package com.bytehonor.sdk.lang.spring.constant;

/**
 * 
 * @author lijianqiang
 *
 */
public enum QueryLogic {

    AND("AND", "AND"),

    OR("OR", "OR"),

    ;

    private String key;

    private String name;

    private QueryLogic(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public static QueryLogic keyOf(String key) {
        if (key == null) {
            return AND;
        }
        key = key.toLowerCase();
        for (QueryLogic item : QueryLogic.values()) {
            if (item.getKey().equals(key)) {
                return item;
            }
        }
        return AND;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
