package com.bytehonor.sdk.lang.spring.meta;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.bytehonor.sdk.lang.spring.string.SpringString;

public class MetaModelUtils {

    private static final Map<String, MetaModel> CACHES = new ConcurrentHashMap<String, MetaModel>();

    public static MetaModel parse(Class<?> clazz) {
        Objects.requireNonNull(clazz, "clazz");

        String name = clazz.getName();
        MetaModel model = CACHES.get(name);
        if (model != null) {
            return model;
        }

        model = new MetaModel();
        model.setName(clazz.getName());

        List<MetaModelField> modelFields = new ArrayList<MetaModelField>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String key = field.getName();
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            MetaModelField modelField = new MetaModelField();
            modelField.setKey(key);
            modelField.setColumn(SpringString.camelToUnderline(key));
            modelField.setType(field.getType().getName());
            modelFields.add(modelField);
        }
        model.setFields(modelFields);

        CACHES.put(name, model);
        return model;
    }
}
