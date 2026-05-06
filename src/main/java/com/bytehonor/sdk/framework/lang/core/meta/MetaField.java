package com.bytehonor.sdk.framework.lang.core.meta;

/**
 * 描述单个 Java Bean 字段的驼峰名、下划线名与类型全限定名。
 *
 * @author lijianqiang
 *
 */
public class MetaField {

    /**
     * 如：updateAt
     */
    private String camel;

    /**
     * 如：update_at
     */
    private String underline;

    /**
     * java类型
     */
    private String type;

    public MetaField() {
        this("", "", "");
    }

    public MetaField(String camel, String underline, String type) {
        this.camel = camel;
        this.underline = underline;
        this.type = type;
    }

    public String getCamel() {
        return camel;
    }

    public void setCamel(String camel) {
        this.camel = camel;
    }

    public String getUnderline() {
        return underline;
    }

    public void setUnderline(String underline) {
        this.underline = underline;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
