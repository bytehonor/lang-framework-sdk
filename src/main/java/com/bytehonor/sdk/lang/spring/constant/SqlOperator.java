package com.bytehonor.sdk.lang.spring.constant;

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

    private String key;

    private String opt;

    private SqlOperator(String key, String opt) {
        this.key = key;
        this.opt = opt;
    }

    public static SqlOperator keyOf(String key) {
        for (SqlOperator sc : SqlOperator.values()) {
            if (sc.key.equals(key)) {
                return sc;
            }
        }
        return EQ;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }
}
