package com.bytehonor.sdk.framework.lang.constant;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * eq 等于
 * neq 不等于
 * gt 大于
 * egt 大于等于
 * lt 小于
 * elt 小于等于
 * like LIKE
 * between BETWEEN
 * notnull IS NUT NULL
 * null IS NULL
 * </pre>
 * 
 * @author lijianqiang
 *
 */
public enum SqlOperator {

    EQ("eq", "="),

    NEQ("neq", "!="),

    GT("gt", ">"),

    EGT("egt", ">="),

    LT("lt", "<"),

    ELT("elt", "<="),

    LIKE("like", "LIKE"),

    LIKE_LEFT("like_left", "LIKE"),

    LIKE_RIGHT("like_right", "LIKE"),

    BETWEEN("between", "BETWEEN"),

    IN("in", "IN"),

    ASC("asc", "ASC"),

    DESC("desc", "DESC"),

    ;

    private static final Map<String, SqlOperator> BY_KEY;

    private static final Map<String, String> MAPPING;

    static {
        Map<String, SqlOperator> m = new HashMap<String, SqlOperator>();
        for (SqlOperator v : values()) {
            SqlOperator prev = m.putIfAbsent(v.key, v);
            if (prev != null) {
                throw new IllegalStateException("duplicate key: " + v.key);
            }
        }
        BY_KEY = Collections.unmodifiableMap(m);

        MAPPING = new HashMap<String, String>();
        MAPPING.put("gte", "egt");
        MAPPING.put("lte", "elt");
    }

    private final String key;

    private final String opt;

    private SqlOperator(String key, String opt) {
        this.key = key;
        this.opt = opt;
    }

    public static SqlOperator keyOf(String key) {
        SqlOperator item = BY_KEY.get(realKey(key));
        if (item == null) {
            return EQ;
        }
        return item;
    }

    private static String realKey(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        return MAPPING.getOrDefault(key, key);
    }

    public String key() {
        return key;
    }

    public String opt() {
        return opt;
    }

    /**
     * 调试或对外序列化时可用；业务比较请优先用枚举引用而非字符串。
     */
    @Override
    public String toString() {
        return name() + "(" + key + ")";
    }
}
