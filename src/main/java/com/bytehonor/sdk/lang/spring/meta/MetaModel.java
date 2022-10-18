package com.bytehonor.sdk.lang.spring.meta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.lang.spring.exception.SpringLangException;

/**
 * @author lijianqiang
 *
 */
public class MetaModel {

    private String name;

    private List<MetaModelField> fields;

    private final Set<String> keys;

    private final Set<String> columns;

    private final Map<String, MetaModelField> map;

    public MetaModel() {
        fields = new ArrayList<MetaModelField>();
        keys = new HashSet<String>();
        columns = new HashSet<String>();
        map = new HashMap<String, MetaModelField>();
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

    public Set<String> getColumns() {
        return columns;
    }

    public void check() {
        if (CollectionUtils.isEmpty(fields)) {
            throw new SpringLangException("no fields");
        }

        for (MetaModelField field : fields) {
            keys.add(field.getKey());
            columns.add(field.getColumn());

            if (map.containsKey(field.getKey()) == false) {
                map.put(field.getKey(), field);
            }

            if (map.containsKey(field.getColumn()) == false) {
                map.put(field.getColumn(), field);
            }
        }
    }

    public boolean hasKey(String key) {
        Objects.requireNonNull(key, "key");
        return keys.contains(key);
    }

    public boolean hasColumn(String column) {
        Objects.requireNonNull(column, "column");
        return columns.contains(column);
    }

    public boolean contains(String field) {
        Objects.requireNonNull(field, "field");
        return keys.contains(field) || columns.contains(field);
    }

    public MetaModelField getIfPresent(String field) {
        Objects.requireNonNull(field, "field");
        return map.get(field);
    }
}
