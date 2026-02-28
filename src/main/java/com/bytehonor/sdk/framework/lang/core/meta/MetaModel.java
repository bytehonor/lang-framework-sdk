package com.bytehonor.sdk.framework.lang.core.meta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.framework.lang.exception.SpringLangException;

/**
 * @author lijianqiang
 *
 */
public class MetaModel {

    private String name;

    private final List<MetaField> fields;

    private final Set<String> camels;

    private final Set<String> underlines;

    private final Map<String, MetaField> map;

    public MetaModel() {
        fields = new ArrayList<MetaField>();
        camels = new HashSet<String>();
        underlines = new HashSet<String>();
        map = new HashMap<String, MetaField>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MetaField> getFields() {
        return fields;
    }

    public Set<String> getCamels() {
        return camels;
    }

    public Set<String> getUnderlines() {
        return underlines;
    }

    public void add(MetaField field) {
        Objects.requireNonNull(field, "field");

        fields.add(field);
        camels.add(field.getCamel());
        underlines.add(field.getUnderline());

        if (map.containsKey(field.getCamel()) == false) {
            map.put(field.getCamel(), field);
        }

        if (map.containsKey(field.getUnderline()) == false) {
            map.put(field.getUnderline(), field);
        }
    }

    public void check() {
        if (CollectionUtils.isEmpty(fields)) {
            throw new SpringLangException("no fields");
        }
    }

    public boolean hasCamel(String field) {
        Objects.requireNonNull(field, "field");
        return camels.contains(field);
    }

    public boolean hasUnderline(String field) {
        Objects.requireNonNull(field, "field");
        return underlines.contains(field);
    }

    public boolean contains(String field) {
        Objects.requireNonNull(field, "field");
        return hasCamel(field) || hasUnderline(field);
    }

    public MetaField getIfPresent(String field) {
        Objects.requireNonNull(field, "field");
        return map.get(field);
    }
}
