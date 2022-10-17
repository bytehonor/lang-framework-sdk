package com.bytehonor.sdk.lang.spring.meta;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lijianqiang
 *
 */
public class MetaModel {

    private String name;

    private List<MetaModelField> fields;

    private Set<String> keys;

    private Set<String> columns;

    public MetaModel() {
        fields = new ArrayList<MetaModelField>();
        keys = new HashSet<String>();
        columns = new HashSet<String>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MetaModelField> getFields() {
        return fields;
    }

    public void setFields(List<MetaModelField> fields) {
        this.fields = fields;
    }

    public Set<String> getKeys() {
        return keys;
    }

    public void setKeys(Set<String> keys) {
        this.keys = keys;
    }

    public Set<String> getColumns() {
        return columns;
    }

    public void setColumns(Set<String> columns) {
        this.columns = columns;
    }

}
